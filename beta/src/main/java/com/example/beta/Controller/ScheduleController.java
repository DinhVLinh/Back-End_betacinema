package com.example.beta.Controller;

import com.example.beta.Model.Schedule;
import com.example.beta.Payload.DTO.ScheduleDto;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Services.Implements.ScheduleServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "/api/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleServices _services;

    @PostMapping(value = "/addschedule")
    private ReponseObject<ScheduleDto> addSchedule(@RequestBody String schedule){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")); }

        }).create();
        var scheduleNew = gson.fromJson(schedule, Schedule.class);
        return _services.addSchedule(scheduleNew);
    }

    @PatchMapping(value = "/updateschedule")
    private ReponseObject<ScheduleDto> updateSchedule(@RequestBody String schedule){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")); }

        }).create();
        var scheduleUpdate = gson.fromJson(schedule, Schedule.class);
        return _services.updateSchedule(scheduleUpdate);
    }

    @DeleteMapping(value = "/deleteschedule")
    private ReponseObject<ScheduleDto> deleteSchedule(@RequestParam int id){
        return _services.deleteSchedule(id);
    }

    @GetMapping(value = "/getschedule")
    private ReponseObject<ScheduleDto> getScheduleById(@RequestParam int id){
        return _services.getScheduleById(id);
    }
}
