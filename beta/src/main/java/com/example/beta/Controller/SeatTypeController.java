package com.example.beta.Controller;

import com.example.beta.Model.SeatType;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Services.Implements.SeatServices;
import com.example.beta.Services.Implements.SeatTypeServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "/api/seattype")
public class SeatTypeController {
    @Autowired
    private SeatTypeServices _services;

    @PostMapping(value = "/addseattype")
    private ReponseObject<SeatType> addSeatType(@RequestBody String seatType){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

        }).create();
        SeatType seatTypeNew = gson.fromJson(seatType,SeatType.class);
        return _services.addSeatType(seatTypeNew);
    }

    @PatchMapping(value = "/updateseattype")
    private ReponseObject<SeatType> updateSeatType(@RequestBody String seatType){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

        }).create();
        SeatType seatTypeUpdate = gson.fromJson(seatType,SeatType.class);
        return _services.updateSeatType(seatTypeUpdate);
    }



    @GetMapping(value = "/getseattypebyid")
    private ReponseObject<SeatType> getSeatType(@RequestParam int id){

        return _services.getSeatTypeById(id);
    }


    @DeleteMapping(value = "/deleteseattype")
    private ReponseObject<SeatType> deleteSeatType(@RequestParam int id){

        return _services.deleteSeatType(id);
    }


}
