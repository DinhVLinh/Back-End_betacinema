package com.example.beta.Controller;

import com.example.beta.Payload.DTO.RoomDto;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Payload.Request.RoomRequest;
import com.example.beta.Services.Implements.RoomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/room")
public class RoomController {
    @Autowired
    private RoomServices _services;

    @PostMapping(value = "/addroom")
    private ReponseObject<RoomDto> addRoom(@RequestBody RoomRequest request){
        return _services.addRoom(request);
    }

    @PatchMapping(value = "/updateroom")
    private ReponseObject<RoomDto> updateRoom(@RequestBody RoomRequest request){
        return _services.updateRoom(request);
    }

    @DeleteMapping(value = "/deleteroom")
    private ReponseObject<RoomDto> deleteRoom(@RequestParam int id){
        return _services.deleteRoom(id);
    }

    @GetMapping(value = "/getroombyid")
    private ReponseObject<RoomDto> getRoomById(@RequestBody int id){
        return _services.getRoomById(id);
    }
}
