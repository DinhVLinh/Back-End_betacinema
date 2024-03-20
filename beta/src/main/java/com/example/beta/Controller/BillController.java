package com.example.beta.Controller;

import com.example.beta.Model.Bill;
import com.example.beta.Payload.DTO.BillDto;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Services.Implements.BillServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/bill")
public class BillController {
    @Autowired
    private BillServices _services;

    @PostMapping(value = "/addbill")
    private ReponseObject<BillDto> addBill(@RequestBody String bill){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

        }).create();

        Bill billNew = gson.fromJson(bill,Bill.class);
        return _services.addBill(billNew);
    }

    @PatchMapping(value = "/updatebill")
    private ReponseObject<BillDto> updateBill(@RequestBody String bill){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

        }).create();

        Bill billUpdate = gson.fromJson(bill,Bill.class);
        return _services.updateBill(billUpdate);
    }

    @GetMapping(value = "/getbillbyid")
    private ReponseObject<BillDto> getBillById(@RequestParam int id){
        return _services.getBillById(id);
    }

    @DeleteMapping(value = "/deletebillbyid")
    private ReponseObject<BillDto> deleteBillById(@RequestParam int id){
        return _services.deleteBill(id);
    }



}
