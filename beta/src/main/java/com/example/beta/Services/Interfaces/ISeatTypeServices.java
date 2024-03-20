package com.example.beta.Services.Interfaces;

import com.example.beta.Model.SeatType;
import com.example.beta.Payload.Reponse.ReponseObject;

public interface ISeatTypeServices {
    ReponseObject<SeatType> addSeatType(SeatType seatType);
    ReponseObject<SeatType> updateSeatType(SeatType seatType);

    ReponseObject<SeatType> getSeatTypeById(int id);
    ReponseObject<SeatType> deleteSeatType(int id);
}
