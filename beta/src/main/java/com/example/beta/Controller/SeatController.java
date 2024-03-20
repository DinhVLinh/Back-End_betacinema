package com.example.beta.Controller;

import com.example.beta.Model.Seat;
import com.example.beta.Payload.DTO.SeatDto;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Services.Implements.SeatServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/seat")
public class SeatController {
    @Autowired
    private SeatServices _services;

    @PostMapping(value = "/addseat")
    private ReponseObject<SeatDto> addSeat(@RequestBody String seat){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")); }

        }).create();
        var seatNew = gson.fromJson(seat, Seat.class);
        return _services.addSeat(seatNew);
    }

    @PatchMapping(value = "/updateseat")
    private ReponseObject<SeatDto> updateSeat(@RequestBody String seat){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")); }

        }).create();
        var seatUpdate = gson.fromJson(seat, Seat.class);
        return _services.updateSeat(seatUpdate);
    }

    @DeleteMapping(value = "/deleteseat")
    private ReponseObject<SeatDto> deleteSeat(@RequestParam int id){
        return _services.deleteSeat(id);
    }
}
