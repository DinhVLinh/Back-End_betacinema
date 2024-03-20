package com.example.beta.Controller;

import com.example.beta.Model.Tickets;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Services.Implements.TicketsServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "/api/tickets")
public class TicketsController {

    @Autowired
    private TicketsServices _services;

    @PostMapping(value = "/addtickets")
    private ReponseObject<Tickets> addTickets(@RequestBody String tickets) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

        }).create();
        Tickets ticketsNew = gson.fromJson(tickets, Tickets.class);
        return _services.addTickets(ticketsNew);
    }

    @PatchMapping(value = "/updatetickets")
    private ReponseObject<Tickets> updateTickets(@RequestBody String tickets) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

        }).create();
        Tickets ticketsUpdate = gson.fromJson(tickets, Tickets.class);
        return _services.updateTickets(ticketsUpdate);
    }


    @GetMapping(value = "/getticketsbyid")
    private ReponseObject<Tickets> getTickets(@RequestParam int id) {

        return _services.getTicketsByid(id);
    }


    @DeleteMapping(value = "/deletetickets")
    private ReponseObject<Tickets> delete(@RequestParam int id) {

        return _services.deleteTickets(id);
    }


}
