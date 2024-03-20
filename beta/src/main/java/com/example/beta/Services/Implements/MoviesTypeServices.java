package com.example.beta.Services.Implements;

import com.example.beta.Model.MovieTypes;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Repository.MoviesTypeRepository;
import com.example.beta.Services.Interfaces.IMoviesTypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;

@Service
public class MoviesTypeServices implements IMoviesTypeServices {
    @Autowired
    private MoviesTypeRepository moviesTypeRepository;
    @Autowired
    private ReponseObject moviesTypeReponse;

    @Override
    public ReponseObject<MovieTypes> addMoviesType(MovieTypes movieTypes) {
        MovieTypes movieTypesNew = MovieTypes.builder()
                .movieTypesName(movieTypes.getMovieTypesName())
                .isActive(movieTypes.getIsActive())
                .build();
        moviesTypeRepository.save(movieTypesNew);
        return moviesTypeReponse.reponseSuccess("add movies type successfully",movieTypesNew);
    }

    @Override
    public ReponseObject<MovieTypes> updateMoviesType(MovieTypes movieTypes) {
        var moviesTypeUpdate = moviesTypeRepository.findById(movieTypes.getId());
        if(moviesTypeUpdate.isEmpty()){
            return moviesTypeReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"movie type not found",null);
        }
        moviesTypeUpdate.get().setMovieTypesName(movieTypes.getMovieTypesName());
        moviesTypeUpdate.get().setIsActive(movieTypes.getIsActive());
        moviesTypeRepository.save(moviesTypeUpdate.get());
        return moviesTypeReponse.reponseSuccess("update movie type successfully",moviesTypeUpdate.get());
    }

    @Override
    public ReponseObject<MovieTypes> deleteMoviesType(int id) {
        var movieTypes = moviesTypeRepository.findById(id);
        if(movieTypes.isEmpty()){
            return moviesTypeReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"movie type not found",null);
        }
       moviesTypeRepository.delete(movieTypes.get());
        return moviesTypeReponse.reponseSuccess("delete movie type successfully",movieTypes.get());
    }

    @Override
    public ReponseObject<MovieTypes> getMoviesTypeById(int id) {
        var movieTypes = moviesTypeRepository.findById(id);
        if(movieTypes.isEmpty()){
            return moviesTypeReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"movie type not found",null);
        }

        return moviesTypeReponse.reponseSuccess("get movie type successfully",movieTypes.get());
    }
}
