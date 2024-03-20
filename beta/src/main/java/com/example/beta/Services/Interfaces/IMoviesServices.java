package com.example.beta.Services.Interfaces;

import com.example.beta.Model.Movies;
import com.example.beta.Payload.DTO.MovieDto;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Payload.Request.MoviesRequest;


public interface IMoviesServices {
    ReponseObject<MovieDto> addMovie(MoviesRequest request);
    ReponseObject<MovieDto> updateMovie(MoviesRequest request);

    ReponseObject<MovieDto> deleteMovie(int id);

    ReponseObject<MovieDto> getMovieById(int id);
}
