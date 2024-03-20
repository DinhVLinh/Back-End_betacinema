package com.example.beta.Controller;

import com.example.beta.Model.Promotions;
import com.example.beta.Payload.DTO.PromotionDto;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Services.Implements.PromotionServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "/api/promotion")
public class PromotionController {
    @Autowired
    private PromotionServices _services;

    @PostMapping(value = "/addpromotion")
    private ReponseObject<PromotionDto> addPromotion(@RequestBody String promotion){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")); }

        }).create();

        var promotionNew = gson.fromJson(promotion, Promotions.class);
        return _services.addPromotion(promotionNew);
    }

    @PatchMapping (value = "/updatepromotion")
    private ReponseObject<PromotionDto> updatePromotion(@RequestBody String promotion){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")); }

        }).create();

        var promotionUpdate = gson.fromJson(promotion, Promotions.class);
        return _services.updatePromotion(promotionUpdate);
    }

    @DeleteMapping(value = "/deletepromotion")
    private  ReponseObject<PromotionDto> deletePromotion(@RequestParam int id){
        return _services.deletePromotion(id);
    }

    @GetMapping(value = "/getpromotionbyid")
    private ReponseObject<PromotionDto> getPromotionById(@RequestParam int id){
        return _services.getPromotionById(id);
    }
}
