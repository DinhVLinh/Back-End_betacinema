package com.example.beta.Services.Interfaces;

import com.example.beta.Model.Seat;
import com.example.beta.Payload.DTO.SeatDto;
import com.example.beta.Payload.Reponse.ReponseObject;

public interface ISeatServices {
    ReponseObject<SeatDto> addSeat(Seat seat);
    ReponseObject<SeatDto> updateSeat(Seat seat);
    ReponseObject<SeatDto> deleteSeat(int id);
}
