package com.example.beta.Services.Implements;

import com.example.beta.Model.Room;
import com.example.beta.Payload.Converter.RoomConverter;
import com.example.beta.Payload.DTO.RoomDto;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Payload.Request.RoomRequest;
import com.example.beta.Repository.RoomRepository;
import com.example.beta.Services.Interfaces.IRoomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;

@Service
public class RoomServices implements IRoomServices {
    @Autowired
    private RoomRepository roomRepo;
    @Autowired
    private ReponseObject roomReponObject;
    @Autowired
    private RoomConverter roomConverter;
    @Override
    public ReponseObject<RoomDto> addRoom(RoomRequest request) {
        Room roomNew = Room.builder()
                .code(request.getCode())
                .capacity(request.getCapacity())
                .type(request.getType())
                .name(request.getName())
                .desctiption(request.getDescription())
                .isActive(request.getIsActive())
                .build();
        roomRepo.save(roomNew);
        return roomReponObject.reponseSuccess("them room thanh cong",roomNew) ;
    }

    @Override
    public ReponseObject<RoomDto> updateRoom(RoomRequest request) {
        var room = roomRepo.findById(request.getId());
        if (room.isEmpty()){
            return roomReponObject.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"khong tim thay room",null);
        }
        room.get().setCapacity(request.getCapacity());
        room.get().setCode(request.getCode());
        room.get().setName(request.getName());
        room.get().setIsActive(request.getIsActive());
        room.get().setCinemaId(request.getCinemaId());
        room.get().setDesctiption(request.getDescription());
        room.get().setType(request.getType());
        roomRepo.save(room.get());
        return roomReponObject.reponseSuccess("sua room thanh cong",roomConverter.roomDto(room.get()));
    }

    @Override
    public ReponseObject<RoomDto> deleteRoom(int id) {
        var room = roomRepo.findById(id);
        if(room.isEmpty()) {
            roomReponObject.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"khong tim thay room",null);
        }
        roomRepo.delete(room.get());
            return roomReponObject.reponseSuccess("xoa room thanh cong",roomConverter.roomDto(room.get()));
    }

    @Override
    public ReponseObject<RoomDto> getRoomById(int id) {
        var room = roomRepo.findById(id);
        if(room.isEmpty()){
            return roomReponObject.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"room not found",null);
        }
        return roomReponObject.reponseSuccess("get room successfully",roomConverter.roomDto(room.get()));
    }


}
