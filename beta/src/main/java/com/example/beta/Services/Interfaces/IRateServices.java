package com.example.beta.Services.Interfaces;

import com.example.beta.Model.Rate;
import com.example.beta.Payload.Reponse.ReponseObject;


public interface IRateServices {
    ReponseObject<Rate> addRate(Rate rate);
    ReponseObject<Rate> updateRate(Rate rate);
    ReponseObject<Rate> getRateById(int id);
    ReponseObject<Rate> deleteRate(int id);
}
