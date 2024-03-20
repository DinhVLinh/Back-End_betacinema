package com.example.beta.Services.Implements;

import com.example.beta.Model.Seat;
import com.example.beta.Payload.Converter.SeatConverter;
import com.example.beta.Payload.DTO.SeatDto;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Repository.SeatRepository;
import com.example.beta.Services.Interfaces.ISeatServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;

@Service
public class SeatServices implements ISeatServices {
    @Autowired
    private SeatRepository seatRepo;
    @Autowired
    private ReponseObject seatReponseObject;
    @Autowired
    private SeatConverter seatConverter;
    @Override
    public ReponseObject<SeatDto> addSeat(Seat seat) {
        Seat newSeat = Seat.builder()
                .seatStatusId(seat.getSeatStatusId())
                .seatTypeId(seat.getSeatTypeId())
                .seatStatusId(seat.getSeatStatusId())
                .isActive(seat.getIsActive())
                .roomId(seat.getRoomId())
                .line(seat.getLine())
                .build();
        seatRepo.save(newSeat);
        return seatReponseObject.reponseSuccess("them seat thanh cong" , seatConverter.seatDto(newSeat));
    }

    @Override
    public ReponseObject<SeatDto> updateSeat(Seat seat) {
        var seatUpdate = seatRepo.findById(seat.getId());
        if(seatUpdate.isEmpty()){
            return seatReponseObject.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"khong tim thay seat",null);
        }
        seatUpdate.get().setIsActive(seat.getIsActive());
        seatUpdate.get().setLine(seat.getLine());
        seatUpdate.get().setNumber(seat.getNumber());
        seatUpdate.get().setSeatStatusId(seat.getSeatStatusId());
        seatUpdate.get().setRoomId(seat.getRoomId());
        seatUpdate.get().setSeatTypeId(seat.getSeatTypeId());
        return seatReponseObject.reponseSuccess("update seat thanh cong",seatConverter.seatDto(seatUpdate.get()));
    }

    @Override
    public ReponseObject<SeatDto> deleteSeat(int id) {
        var seat = seatRepo.findById(id);
        if(seat.isEmpty()){
            return seatReponseObject.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"khong tim thay seat",null);
        }
        seatRepo.delete(seat.get());
        return seatReponseObject.reponseSuccess("xoa seat thanh cong",seatConverter.seatDto(seat.get()));
    }


}
