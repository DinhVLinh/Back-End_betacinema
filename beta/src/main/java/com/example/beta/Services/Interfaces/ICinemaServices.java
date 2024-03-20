package com.example.beta.Services.Interfaces;

import com.example.beta.Model.Cinema;
import com.example.beta.Payload.Reponse.ReponseObject;

public interface ICinemaServices {

    ReponseObject<Cinema> addCinema(Cinema cinema);
    ReponseObject<Cinema> updateCinema( Cinema cinema);
    ReponseObject<String> deleteCinema(int id);
    ReponseObject<Cinema> getCinemaById(int id);
}
