package com.example.beta.Services.Interfaces;

import com.example.beta.Model.SeatStatus;
import com.example.beta.Model.SeatType;
import com.example.beta.Payload.Reponse.ReponseObject;

public interface ISeatStatusServices {
    ReponseObject<SeatStatus> addSeatStatus(SeatStatus seatStatus);
    ReponseObject<SeatStatus> updateSeatStatus(SeatStatus seatStatus);

    ReponseObject<SeatStatus> getSeatStatusById(int id);
    ReponseObject<SeatStatus> deleteSeatStatus(int id);
}
