package com.example.beta.Controller;

import com.example.beta.Model.BillTickets;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Services.Implements.BillTicketsServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "/api/billtickets")
public class BillTicketController {
    @Autowired
    private BillTicketsServices _services;

    @PostMapping(value = "/addbillticket")
    private ReponseObject<BillTickets> addBillTickets(@RequestBody String billTickets){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

        }).create();
        BillTickets billTicketsNew = gson.fromJson(billTickets,BillTickets.class);

        return _services.addBillTicket(billTicketsNew);
    }

    @PatchMapping("/updatebillticket")
    private ReponseObject<BillTickets> updateBillTickets(@RequestBody String billTickets){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

        }).create();
        BillTickets billTicketsUpdate = gson.fromJson(billTickets,BillTickets.class);

        return _services.updateBillTicket(billTicketsUpdate);
    }

    @GetMapping("/getbillticketbyid")
    private ReponseObject<BillTickets> getBillTickets(@RequestParam int id){

        return _services.getBillTicketById(id);
    }

    @DeleteMapping("/deletebillticketbyid")
    private ReponseObject<BillTickets> deleteBillTickets(@RequestParam int id){

        return _services.deleteBillTickets(id);
    }
}
