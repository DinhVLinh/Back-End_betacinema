package com.example.beta.Controller;

import com.example.beta.Model.SeatStatus;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Services.Implements.SeatStatusServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "/api/seatstatus")
public class SeatStatusController {
    @Autowired
    private SeatStatusServices _services;


    @PostMapping(value = "/addseatstatus")
    private ReponseObject<SeatStatus> addSeatStatus(@RequestBody String seatStatus){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

        }).create();

        SeatStatus seatStatusNew = gson.fromJson(seatStatus,SeatStatus.class);
        return _services.addSeatStatus(seatStatusNew);
    }

    @PostMapping(value = "/updateseatstatus")
    private ReponseObject<SeatStatus> updateSeatStatus(@RequestBody String seatStatus){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

        }).create();

        SeatStatus seatStatusUpdate = gson.fromJson(seatStatus,SeatStatus.class);
        return _services.updateSeatStatus(seatStatusUpdate);
    }



    @GetMapping(value = "/getseatstatusbyid")
    private ReponseObject<SeatStatus> getSeatStatusById(@RequestParam int id){

        return _services.getSeatStatusById(id);
    }

    @DeleteMapping(value = "/deleteseatstatusbyid")
    private ReponseObject<SeatStatus> deleteSeatStatusById(@RequestParam int id){

        return _services.deleteSeatStatus(id);
    }


}
