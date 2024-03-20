package com.example.beta.Controller;

import com.example.beta.Model.Role;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Services.Implements.RoleServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "/api/role")
public class RoleController {
    @Autowired
    private RoleServices _services;

    @PostMapping("/addrole")
    private ReponseObject<Role> addRole(@RequestBody String role){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")); }

        }).create();

        Role roleNew = gson.fromJson(role,Role.class);
        return _services.addRole(roleNew);
    }


    @PatchMapping("/updaterole")
    private ReponseObject<Role> updateRole(@RequestBody String role){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")); }

        }).create();

        Role roleUpdate = gson.fromJson(role,Role.class);
        return _services.updateRole(roleUpdate);
    }

    @GetMapping("/getrolebyid")
    private ReponseObject<Role> getRoleById(@RequestParam int id){

        return _services.getRoleById(id);
    }

    @GetMapping("/deleterolebyid")
    private ReponseObject<Role> deleteRoleByid(@RequestParam int id){

        return _services.deleteRole(id);
    }
}
