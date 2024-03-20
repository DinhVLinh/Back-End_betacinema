package com.example.beta.Services.Implements;

import com.example.beta.Model.SeatStatus;
import com.example.beta.Model.SeatType;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Repository.SeatStatusRepository;
import com.example.beta.Services.Interfaces.ISeatStatusServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;

@Service
public class SeatStatusServices implements ISeatStatusServices {
    @Autowired
    private SeatStatusRepository seatStatusRepository;
    @Autowired
    private ReponseObject seatStatusReponse;

    @Override
    public ReponseObject<SeatStatus> addSeatStatus(SeatStatus seatStatus) {
        SeatStatus seatStatusNew = SeatStatus.builder()
                .nameStatus(seatStatus.getNameStatus())
                .code(seatStatus.getCode())
                .build();
        seatStatusRepository.save(seatStatusNew);
        return seatStatusReponse.reponseSuccess("add seat status successfully", seatStatusNew);
    }

    @Override
    public ReponseObject<SeatStatus> updateSeatStatus(SeatStatus seatStatus) {
        var seatStatusUpdate = seatStatusRepository.findById(seatStatus.getId());
        if(seatStatusUpdate.isEmpty()){
            return seatStatusReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"seat status not found",null);
        }
        seatStatusUpdate.get().setNameStatus(seatStatus.getNameStatus());
        seatStatusUpdate.get().setCode(seatStatus.getCode());
        seatStatusRepository.save(seatStatusUpdate.get());
        return seatStatusReponse.reponseSuccess("update seat status successfully",seatStatusUpdate.get());
    }

    @Override
    public ReponseObject<SeatStatus> getSeatStatusById(int id) {
        var seatStatus = seatStatusRepository.findById(id);
        if(seatStatus.isEmpty()){
            return seatStatusReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"seat status not found",null);
        }

        return seatStatusReponse.reponseSuccess("get seat status successfully",seatStatus.get());
    }

    @Override
    public ReponseObject<SeatStatus> deleteSeatStatus(int id) {
        var seatStatus = seatStatusRepository.findById(id);
        if(seatStatus.isEmpty()){
            return seatStatusReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"seat status not found",null);
        }
        seatStatusRepository.delete(seatStatus.get());
        return seatStatusReponse.reponseSuccess("get seat status successfully",seatStatus.get());

    }
}
