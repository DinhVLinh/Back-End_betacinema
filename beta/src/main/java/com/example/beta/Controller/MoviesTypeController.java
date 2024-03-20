package com.example.beta.Controller;

import com.example.beta.Model.MovieTypes;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Services.Implements.MoviesTypeServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "/api/movietype")
public class MoviesTypeController {
    @Autowired
    private MoviesTypeServices _services;

    @PostMapping(value = "/addmovietype")
    private ReponseObject<MovieTypes> addMovieType(@RequestBody String movieType){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

        }).create();

        MovieTypes movieTypes = gson.fromJson(movieType,MovieTypes.class);
        return _services.addMoviesType(movieTypes);
    }

    @PatchMapping(value = "/updatemovietype")
    private ReponseObject<MovieTypes> patchMovieType(@RequestBody String movieType){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

        }).create();

        MovieTypes movieTypes = gson.fromJson(movieType,MovieTypes.class);
        return _services.updateMoviesType(movieTypes);
    }

    @GetMapping(value = "/getmovietypebyid")
    private ReponseObject<MovieTypes> getMovieTypeById(@RequestParam int id){
        return _services.getMoviesTypeById(id);
    }

    @DeleteMapping(value = "/deletemovietypebyid")
    private ReponseObject<MovieTypes> deleteMovieTypeById(@RequestParam int id){
        return _services.deleteMoviesType(id);
    }
}
