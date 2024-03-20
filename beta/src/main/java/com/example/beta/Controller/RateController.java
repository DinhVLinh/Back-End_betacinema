package com.example.beta.Controller;

import com.example.beta.Model.Rate;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Services.Implements.RateServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "/api/rate")
public class RateController {
    @Autowired
    private RateServices _services;

    @PostMapping(value = "/addrate")
    private ReponseObject<Rate> addRate(@RequestBody String rate){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")); }

        }).create();

        Rate rateNew = gson.fromJson(rate,Rate.class);
        return _services.addRate(rateNew);
    }

    @PatchMapping(value = "/updaterate")
    private ReponseObject<Rate> updateRate(@RequestBody String rate){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")); }

        }).create();

        Rate rateUpdate = gson.fromJson(rate,Rate.class);
        return _services.updateRate(rateUpdate);
    }


    @GetMapping(value = "/getratebyId")
    private ReponseObject<Rate> getRateById(@RequestParam int id){

        return _services.getRateById(id);
    }

    @DeleteMapping(value = "/deleteratebyid")
    private ReponseObject<Rate> deleteRateById(@RequestParam int id){
        return _services.deleteRate(id);
    }
}
