package com.example.beta.Services.Implements;

import com.example.beta.Model.Movies;
import com.example.beta.Payload.Converter.MoviesConverter;
import com.example.beta.Payload.DTO.MovieDto;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Payload.Request.MoviesRequest;
import com.example.beta.Repository.MoviesRepository;
import com.example.beta.Services.Interfaces.IMoviesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;

@Service
public class MoviesServices implements IMoviesServices {
    @Autowired
    private MoviesRepository moviesRepo;
    @Autowired
    private ReponseObject movieReponse;
    @Autowired
    private MoviesConverter converter;
    @Override
    public ReponseObject<MovieDto> addMovie(MoviesRequest request) {
        var movie = Movies.builder()
                .movieTypeId(request.getMovieTypeId())
                .movieDuration(request.getMovieDuration())
                .endTime(request.getEndTime())
                .description(request.getDescription())
                .image(request.getImage())
                .premiereDate(request.getPremiereDate())
                .name(request.getName())
                .isActive(request.isActive())
                .language(request.getLanguage())
                .heroImage(request.getHeroImage())
                .trailer(request.getTrailer())
                .directior(request.getDirector())
                .rateid(request.getRateId())
                .movieTypeId(request.getMovieTypeId())
                .build();
        moviesRepo.save(movie);

        return movieReponse.reponseSuccess("add movies successfully",converter.movieDto(movie));
    }

    @Override
    public ReponseObject<MovieDto> updateMovie(MoviesRequest request) {
        var movie = moviesRepo.findById(request.getId());
        if (movie.isEmpty()){
            return movieReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"movie not found",null);
        }
        movie.get().setMovieDuration(request.getMovieDuration());
        movie.get().setMovieTypeId(request.getMovieTypeId());
        movie.get().setDescription(request.getDescription());
        movie.get().setEndTime(request.getEndTime());
        movie.get().setImage(request.getImage());
        movie.get().setHeroImage(request.getHeroImage());
        movie.get().setMovieTypeId(request.getMovieTypeId());
        movie.get().setPremiereDate(request.getPremiereDate());
        movie.get().setLanguage(request.getLanguage());
        movie.get().setTrailer(request.getTrailer());
        movie.get().setName(request.getName());
        movie.get().setIsActive(request.isActive());
        movie.get().setRateid(request.getRateId());
        moviesRepo.save(movie.get());
        return movieReponse.reponseSuccess("update movies successfully",converter.movieDto(movie.get()));
    }

    @Override
    public ReponseObject<MovieDto> deleteMovie(int id) {
        var movies = moviesRepo.findById(id);
        if(movies.isEmpty()){
            return movieReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST, "not found",null);
        }

        moviesRepo.delete(movies.get());
        return movieReponse.reponseSuccess("delete movies successfully",converter.movieDto(movies.get()));
    }

    @Override
    public ReponseObject<MovieDto> getMovieById(int id) {
        var movie = moviesRepo.findById(id);
        if(movie.isEmpty()){
            return movieReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"movie not found",null);
        }
        return movieReponse.reponseSuccess("get movie successfully",converter.movieDto(movie.get()));
    }
}
