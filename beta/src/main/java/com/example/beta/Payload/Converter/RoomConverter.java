package com.example.beta.Payload.Converter;

import com.example.beta.Model.Room;
import com.example.beta.Payload.DTO.RoomDto;
import org.springframework.stereotype.Component;

@Component
public class RoomConverter {
    public RoomDto roomDto(Room room){
        RoomDto roomDto = new RoomDto();
        roomDto.setName(room.getName());
        roomDto.setIsActive(room.getIsActive());
        roomDto.setType(room.getType());
        roomDto.setCode(room.getCode());
        roomDto.setDescription(room.getDesctiption());
        roomDto.setCapacity(roomDto.getCapacity());
        return roomDto;
    }
}
