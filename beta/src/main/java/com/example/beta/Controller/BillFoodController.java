package com.example.beta.Controller;

import com.example.beta.Model.BillFood;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Services.Implements.BillFoodServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "/api/billfood")
public class BillFoodController {
    @Autowired
    private BillFoodServices _services;

    @PostMapping(value = "/addbillfood")
    private ReponseObject<BillFood> addBillFood(@RequestBody String billFood){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

        }).create();

        BillFood billFoodNew = gson.fromJson(billFood,BillFood.class);
        return _services.addBillFood(billFoodNew);
    }

    @PatchMapping(value = "/updatebillfood")
    private ReponseObject<BillFood> updateBillFood(@RequestBody String billFood){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

        }).create();

        BillFood billFoodUpdate = gson.fromJson(billFood,BillFood.class);
        return _services.updateBillFood(billFoodUpdate);
    }

    @GetMapping(value = "/getbillfoodbyid")
    private ReponseObject<BillFood> getBillFoodById(@RequestParam int id){
        return _services.getBillFoodById(id);
    }

    @DeleteMapping(value = "/deletebillfood")
    private ReponseObject<BillFood> deleteBillFood(@RequestBody int id){

        return _services.deleteBillFood(id);
    }
}
