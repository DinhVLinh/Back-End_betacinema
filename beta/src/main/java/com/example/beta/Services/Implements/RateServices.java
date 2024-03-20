package com.example.beta.Services.Implements;

import com.example.beta.Model.Rate;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Repository.RateRepository;
import com.example.beta.Services.Interfaces.IRateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;

@Service
public class RateServices implements IRateServices {
    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private ReponseObject rateReponse;

    @Override
    public ReponseObject<Rate> addRate(Rate rate) {
        Rate rateNew = Rate.builder()
                .code(rate.getCode())
                .description(rate.getDescription())
                .build();
        rateRepository.save(rateNew);
        return rateReponse.reponseSuccess("add rate successfully",rate);
    }

    @Override
    public ReponseObject<Rate> updateRate(Rate rate) {
        var rateUpdate = rateRepository.findById(rate.getId());
        if(rateUpdate.isEmpty()){
            return rateReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"rate not found",null);
        }
        rateUpdate.get().setCode(rate.getCode());
        rateUpdate.get().setDescription(rate.getDescription());
        rateRepository.save(rateUpdate.get());
        return rateReponse.reponseSuccess("update rate successfully",rateUpdate.get());
    }

    @Override
    public ReponseObject<Rate> getRateById(int id) {
        var rate = rateRepository.findById(id);
        if(rate.isEmpty()){
            return rateReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"rate not found",null);
        }

        return rateReponse.reponseSuccess("update rate successfully",rate.get());
    }

    @Override
    public ReponseObject<Rate> deleteRate(int id) {
        var rate = rateRepository.findById(id);
        if(rate.isEmpty()){
            return rateReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"rate not found",null);
        }
        rateRepository.delete(rate.get());
        return rateReponse.reponseSuccess("update rate successfully",rate.get());
    }
}
