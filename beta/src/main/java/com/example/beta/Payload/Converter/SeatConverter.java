package com.example.beta.Payload.Converter;

import com.example.beta.Model.Seat;
import com.example.beta.Payload.DTO.SeatDto;
import org.springframework.stereotype.Component;

@Component
public class SeatConverter {
    public SeatDto seatDto(Seat seat){
        SeatDto seatDto= new SeatDto();
        seatDto.setActive(seat.getIsActive());
        seatDto.setLine(seat.getLine());
        seatDto.setNumber(seat.getNumber());
        return seatDto;
    }
}
