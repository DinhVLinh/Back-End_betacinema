package com.example.beta.Payload.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MovieDto {
    private int movieDuration;
    private LocalDate endTime;
    private LocalDate premiereDate;
    private String description;
    private String director;
    private String image;
    private String heroImage;
    private String language;
    private String name;
    private String trailer;
    private boolean isActive;
}

