package com.example.beta.Payload.Request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MoviesRequest {
    private int id;
    private int movieDuration;
    private LocalDate endTime;
    private LocalDate premiereDate;
    private String description;
    private String director;
    private String image;
    private String heroImage;
    private String language;
    private int movieTypeId;
    private String name;
    private int rateId;
    private String trailer;
    private boolean isActive;
}
