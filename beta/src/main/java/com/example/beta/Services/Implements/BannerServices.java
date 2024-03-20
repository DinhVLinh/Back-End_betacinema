package com.example.beta.Services.Implements;

import com.example.beta.Model.Banner;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Repository.BannerRepository;
import com.example.beta.Services.Interfaces.IBannerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;

@Service
public class BannerServices implements IBannerServices {
    @Autowired
    private BannerRepository bannerRepository;
    @Autowired
    private ReponseObject bannerReponse;
    @Override
    public ReponseObject<Banner> addBanner(Banner banner) {
        Banner bannerNew = Banner.builder()
                .title(banner.getTitle())
                .imageUrl(banner.getImageUrl())
                .build();
        bannerRepository.save(bannerNew);
        return bannerReponse.reponseSuccess("add banner successfully",bannerNew);
    }

    @Override
    public ReponseObject<Banner> updateBanner(Banner banner) {
        var bannerUpdate = bannerRepository.findById(banner.getId());
        if(bannerUpdate.isEmpty()){
            return bannerReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"banner not found",null);
        }

        bannerUpdate.get().setTitle(banner.getTitle());
        bannerUpdate.get().setImageUrl(banner.getImageUrl());
        bannerRepository.save(bannerUpdate.get());
        return bannerReponse.reponseSuccess("add banner successfully", bannerUpdate.get());
    }

    @Override
    public ReponseObject<Banner> getBannerById(int id) {
        var banner = bannerRepository.findById(id);
        if(banner.isEmpty()){
            return bannerReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"banner not found",null);
        }

        return bannerReponse.reponseSuccess("get banner successfully", banner.get());
    }

    @Override
    public ReponseObject<Banner> deleteBanner(int id) {
        var banner = bannerRepository.findById(id);
        if(banner.isEmpty()){
            return bannerReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"banner not found",null);
        }
        bannerRepository.delete(banner.get());
        return bannerReponse.reponseSuccess("delete banner successfully", banner.get());
    }
}
