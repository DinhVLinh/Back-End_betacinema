package com.example.beta.Services.Interfaces;

import com.example.beta.Model.MovieTypes;
import com.example.beta.Payload.Reponse.ReponseObject;

public interface IMoviesTypeServices {
    ReponseObject<MovieTypes> addMoviesType(MovieTypes movieTypes);
    ReponseObject<MovieTypes> updateMoviesType(MovieTypes movieTypes);
    ReponseObject<MovieTypes> deleteMoviesType(int id);
    ReponseObject<MovieTypes> getMoviesTypeById(int id);
}
