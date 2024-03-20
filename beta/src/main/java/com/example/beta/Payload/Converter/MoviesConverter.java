package com.example.beta.Payload.Converter;

import com.example.beta.Model.Movies;
import com.example.beta.Payload.DTO.MovieDto;
import org.springframework.stereotype.Component;

@Component
public class MoviesConverter {
    public MovieDto movieDto(Movies movies){
        MovieDto movieDto = new MovieDto();
        movieDto.setMovieDuration(movies.getMovieDuration());
        movieDto.setDescription(movies.getDescription());
        movieDto.setActive(movieDto.isActive());
        movieDto.setName(movies.getName());
        movieDto.setImage(movies.getImage());
        movieDto.setHeroImage(movies.getHeroImage());
        movieDto.setTrailer(movieDto.getTrailer());
        movieDto.setEndTime(movieDto.getEndTime());
        movieDto.setLanguage(movieDto.getLanguage());
        movieDto.setDirector(movieDto.getDirector());
        movieDto.setPremiereDate(movies.getPremiereDate());

        return movieDto;
    }
}
