package com.example.beta.Controller;

import com.example.beta.Model.Cinema;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Services.Implements.CinemaServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/cinema")
public class CinemaController {
    @Autowired
    private CinemaServices _services;

    @PostMapping("/addcinema")
    private ReponseObject<Cinema> addCinema(@RequestBody String cinema){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")); }

        }).create();
        var cinemaNew = gson.fromJson(cinema,Cinema.class);
        return _services.addCinema(cinemaNew);
    }

    @PatchMapping ("/updatecinema")
    private ReponseObject<Cinema> updateCinema(@RequestBody String cinema){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")); }

        }).create();
        var cinemaNew = gson.fromJson(cinema,Cinema.class);
        return _services.updateCinema(cinemaNew);

    }
    @DeleteMapping("/deletemcinema")
    private ReponseObject<String> deleteCinema(@RequestParam Integer id){
       return _services.deleteCinema(id);
    }

    @GetMapping("/getcinema")
    private ReponseObject<Cinema> getCinemaById(@RequestParam Integer id){
        return _services.getCinemaById(id);
    }
}

