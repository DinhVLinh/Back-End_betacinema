package com.example.beta.Controller;

import com.example.beta.Model.GeneralSetting;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Services.Implements.GeneralSettingServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "/api/generalsetting")
public class GeneralSettingController {
    @Autowired
    private GeneralSettingServices _services;

    @PostMapping(value = "/addgeneralsetting")
    private ReponseObject<GeneralSetting> addGeneralSetting(@RequestBody String generalSetting){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

        }).create();
        GeneralSetting generalSettingNew = gson.fromJson(generalSetting,GeneralSetting.class);
        return _services.addGenerateSetting(generalSettingNew);
    }

    @PatchMapping(value = "/updategeneralsetting")
    private ReponseObject<GeneralSetting> updateGeneralSetting(@RequestBody String generalSetting){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

        }).create();
        GeneralSetting generalSettingUpdate= gson.fromJson(generalSetting,GeneralSetting.class);
        return _services.updateGenerateSetting(generalSettingUpdate);
    }

    @GetMapping(value = "/getgeneralsetting")
    private ReponseObject<GeneralSetting> getGeneralSetting(@RequestParam int id){

        return _services.getGenerateSettingById(id);
    }


    @DeleteMapping(value = "/deletegeneralsetting")
    private ReponseObject<GeneralSetting> deleteGeneralSetting(@RequestParam int id){

        return _services.deleteGenerateSettingById(id);
    }


}
