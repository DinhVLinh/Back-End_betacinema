package com.example.beta.Controller;

import com.example.beta.Model.BillTickets;
import com.example.beta.Model.UserStatus;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Services.Implements.UserStatusServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "/api/userstatus")
public class UserStatusController {
    @Autowired
    private UserStatusServices _services;

    @PostMapping(value = "/api/adduserstatus")
    private ReponseObject<UserStatus> addUserStatus(@RequestBody String userStatus){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

        }).create();
        UserStatus userStatusNew = gson.fromJson(userStatus,UserStatus.class);
        return _services.addUserStatusServices(userStatusNew);

    }

    @PatchMapping(value = "/api/updateuserstatus")
    private ReponseObject<UserStatus> updateUserStatus(@RequestBody String userStatus){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

        }).create();
        UserStatus userStatusUpdate = gson.fromJson(userStatus,UserStatus.class);
        return _services.updateUserStatusServices(userStatusUpdate);

    }


    @GetMapping (value = "/api/getuserstatusbyid")
    private ReponseObject<UserStatus> getUserStatus(@RequestParam int id){

        return _services.getUserStatusById( id);

    }

    @GetMapping (value = "/api/deleteuserstatusbyid")
    private ReponseObject<UserStatus> deleteUserStatus(@RequestParam int id){

        return _services.deleteUserStatus( id);

    }
}
