package com.example.beta.Services.Interfaces;

import com.example.beta.Model.Room;
import com.example.beta.Payload.DTO.RoomDto;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Payload.Request.RoomRequest;

public interface IRoomServices {
    ReponseObject<RoomDto> addRoom(RoomRequest request);

    ReponseObject<RoomDto> updateRoom(RoomRequest request);

    ReponseObject<RoomDto> deleteRoom(int id);

    ReponseObject<RoomDto> getRoomById(int id);
}
