package com.example.beta.Controller;

import com.example.beta.Model.Food;
import com.example.beta.Model.Movies;
import com.example.beta.Payload.DTO.MovieDto;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Payload.Request.MoviesRequest;
import com.example.beta.Services.Implements.MoviesServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "/api/movie")
public class MovieController {

    @Autowired
    private MoviesServices _services;

    @PostMapping(value = "/addmovie")
    private ReponseObject<MovieDto> addMovie(@RequestBody MoviesRequest request){

        return _services.addMovie(request);
    }


    @PatchMapping(value = "/updatemovie")
    private ReponseObject<MovieDto> updateMovie(@RequestBody MoviesRequest request){

        return _services.updateMovie(request);
    }


    @PostMapping(value = "/deletemovie")
    private ReponseObject<MovieDto> deleteMovie(@RequestParam int id){

        return _services.deleteMovie(id);
    }

    @GetMapping(value = "/getmoviebyid")
    private ReponseObject<MovieDto> getFoodById(@RequestParam int id){
        return _services.getMovieById(id);
    }
}
