package com.example.beta.Controller;

import com.example.beta.Model.Food;
import com.example.beta.Model.Seat;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Services.Implements.FoodServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "/api/food")
public class FoodController {
    @Autowired
    private FoodServices _services;

    @PostMapping(value = "/addfood")
    private ReponseObject<Food> addFood(@RequestBody String food){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")); }

        }).create();
        var foodNew = gson.fromJson(food, Food.class);
        return _services.addFood(foodNew);
    }

    @PatchMapping(value = "/updatefood")
    private ReponseObject<Food> updateFood(@RequestBody String food){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")); }

        }).create();
        var foodUpdate = gson.fromJson(food, Food.class);
        return _services.updateFood(foodUpdate);
    }

    @DeleteMapping(value = "/deletefood")
    private ReponseObject<Food> deleteFood(@RequestParam int id){
        return _services.deleteFood(id);
    }

    @GetMapping(value = "/getfoodbyid")
    private ReponseObject<Food> getFoodById(@RequestParam int id){
        return _services.getFoodById(id);
    }
}
