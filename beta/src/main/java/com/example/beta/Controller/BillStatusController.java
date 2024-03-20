package com.example.beta.Controller;

import com.example.beta.Model.BillStatus;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Services.Implements.BillStatusServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "/api/billstatus")
public class BillStatusController {
    @Autowired
    private BillStatusServices _services;

    @PostMapping(value = "/addbillstatus")
    private ReponseObject<BillStatus> addBillStatus(@RequestBody String billStatus){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

        }).create();

        BillStatus billStatusNew = gson.fromJson(billStatus,BillStatus.class);
        return _services.addBillStatus(billStatusNew);
    }

    @PatchMapping(value = "/updatebillstatus")
    private ReponseObject<BillStatus> patchBillStatus(@RequestBody String billStatus){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

        }).create();

        BillStatus billStatusUpdate = gson.fromJson(billStatus,BillStatus.class);
        return _services.updateBillStatus( billStatusUpdate);
    }

    @GetMapping(value = "/getbillstatusbyid")
    private ReponseObject<BillStatus> getBillStatus(@RequestParam int id){

        return _services.getBillStatusById( id);
    }

    @DeleteMapping(value = "/deletebillstatusbyid")
    private ReponseObject<BillStatus> deleteBillStatus(@RequestParam int id){

        return _services.deleteBillStatusById(id);
    }
}
