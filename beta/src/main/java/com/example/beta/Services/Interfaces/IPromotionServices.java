package com.example.beta.Services.Interfaces;

import com.example.beta.Model.Promotions;
import com.example.beta.Payload.DTO.PromotionDto;
import com.example.beta.Payload.Reponse.ReponseObject;

public interface IPromotionServices {
    ReponseObject<PromotionDto> addPromotion(Promotions promotions);
    ReponseObject<PromotionDto> updatePromotion(Promotions promotions);

    ReponseObject<PromotionDto> deletePromotion(int id);
    ReponseObject<PromotionDto> getPromotionById(int id);
}
