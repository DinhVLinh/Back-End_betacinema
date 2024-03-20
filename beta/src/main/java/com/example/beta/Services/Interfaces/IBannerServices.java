package com.example.beta.Services.Interfaces;

import com.example.beta.Model.Banner;
import com.example.beta.Payload.Reponse.ReponseObject;

public interface IBannerServices {
    ReponseObject<Banner> addBanner(Banner banner);
    ReponseObject<Banner> updateBanner(Banner banner);
    ReponseObject<Banner> getBannerById(int id);
    ReponseObject<Banner> deleteBanner(int id);

}
