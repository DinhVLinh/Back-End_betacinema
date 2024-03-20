package com.example.beta.Controller;

import com.example.beta.Model.Banner;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Services.Implements.BannerServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "/api/banner")
public class BannerController {
    @Autowired
    private BannerServices _services;

    @PostMapping(value = "/addbanner")
    private ReponseObject<Banner> addBanner(@RequestBody String banner){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

        }).create();

        Banner bannerNew = gson.fromJson(banner,Banner.class);

        return _services.addBanner(bannerNew);

    }

    @PatchMapping(value = "/updatebanner")
    private ReponseObject<Banner> updateBanner(@RequestBody String banner){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

        }).create();

        Banner bannerUpdate = gson.fromJson(banner,Banner.class);

        return _services.updateBanner(bannerUpdate);

    }

    @GetMapping(value = "/getbannerbyid")
    private ReponseObject<Banner> getBannerById(@RequestParam int id){

        return _services.getBannerById(id);

    }

    @DeleteMapping(value = "/deletebannerbyid")
    private ReponseObject<Banner> deleteBannerById(@RequestParam int id){
        return _services.deleteBanner(id);

    }
}
