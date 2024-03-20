package com.example.beta.Services.Implements;

import com.example.beta.Model.SeatType;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Repository.SeatTypeRepository;
import com.example.beta.Services.Interfaces.ISeatTypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;

@Service
public class SeatTypeServices implements ISeatTypeServices {
    @Autowired
    private SeatTypeRepository seatTypeRepository;
    @Autowired
    private ReponseObject seatTypeReponse;
    @Override
    public ReponseObject<SeatType> addSeatType(SeatType seatType) {
        SeatType seatTypeNew = SeatType.builder().nameType(seatType.getNameType()).build();
        seatTypeRepository.save(seatTypeNew);
        return seatTypeReponse.reponseSuccess("add seat type successfully",seatTypeNew);
    }

    @Override
    public ReponseObject<SeatType> updateSeatType(SeatType seatType) {
        var seatTypeUpdate = seatTypeRepository.findById(seatType.getId());
        if(seatTypeUpdate.isEmpty()){
            return seatTypeReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"seat type not found",null);
        }
        seatTypeUpdate.get().setNameType(seatType.getNameType());
        seatTypeRepository.save(seatTypeUpdate.get());
        return seatTypeReponse.reponseSuccess("update seat type succesfully",seatTypeUpdate.get());
    }

    @Override
    public ReponseObject<SeatType> getSeatTypeById(int id) {
        var seatType = seatTypeRepository.findById(id);
        if(seatType.isEmpty()){
            return seatTypeReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"seat type not found",null);
        }

        return seatTypeReponse.reponseSuccess("get seat type succesfully",seatType.get());

    }

    @Override
    public ReponseObject<SeatType> deleteSeatType(int id) {
        var seatType = seatTypeRepository.findById(id);
        if(seatType.isEmpty()){
            return seatTypeReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"seat type not found",null);
        }
        seatTypeRepository.delete(seatType.get());
        return seatTypeReponse.reponseSuccess("delete seat type succesfully",seatType.get());


    }
}
