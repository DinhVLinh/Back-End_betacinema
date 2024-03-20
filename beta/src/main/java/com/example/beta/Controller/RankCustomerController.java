package com.example.beta.Controller;

import com.example.beta.Model.RankCustomer;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Services.Implements.RankCustomerServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "/api/rankcustomer")
public class RankCustomerController {
    @Autowired
    private RankCustomerServices _services;

    @PostMapping(value = "/addrankcustomer")
    private ReponseObject<RankCustomer> addRankCustomer(@RequestBody String rankCustomer){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

        }).create();
        RankCustomer rankCustomerNew =gson.fromJson(rankCustomer,RankCustomer.class);
        return _services.addRankCustomer(rankCustomerNew);
    }

    @PatchMapping(value = "/updaterankcustomer")
    private ReponseObject<RankCustomer> updateRankCustomer(@RequestBody String rankCustomer){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

        }).create();
        RankCustomer rankCustomerUpdate =gson.fromJson(rankCustomer,RankCustomer.class);
        return _services.updateRankCustomer(rankCustomerUpdate);
    }

    @GetMapping(value = "/getrankcustomerbyid")
    private ReponseObject<RankCustomer> getRankCustomer(@RequestParam int id){

        return _services.getRankCustomer(id);
    }
    @DeleteMapping(value = "/deleterankcustomerbyid")
    private ReponseObject<RankCustomer> deleteRankCustomer(@RequestParam int id){
        return _services.deleteRankCustomer(id);
    }
}
