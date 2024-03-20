package com.example.beta.Services.Implements;

import com.example.beta.Model.Promotions;
import com.example.beta.Payload.Converter.PromotionConverter;
import com.example.beta.Payload.DTO.PromotionDto;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Repository.PromotionRepository;
import com.example.beta.Services.Interfaces.IPromotionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;

@Service
public class PromotionServices implements IPromotionServices {
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private PromotionConverter promotionConverter;
    @Autowired
    private ReponseObject promotionReponse;

    @Override
    public ReponseObject<PromotionDto> addPromotion(Promotions promotions) {
        var promotionNew = Promotions.builder()
                .name(promotions.getName())
                .descsription(promotions.getDescsription())
                .percent(promotions.getPercent())
                .quantity(promotions.getQuantity())
                .endTime(promotions.getEndTime())
                .startTime(promotions.getStartTime())
                .isActive(promotions.getIsActive())
                .rankCustomerId(promotions.getRankCustomerId())
                .type(promotions.getType())
                .build();
        promotionRepository.save(promotionNew);
        return promotionReponse.reponseSuccess("add promotion successfully",promotionConverter.promotionDto(promotions));
    }

    @Override
    public ReponseObject<PromotionDto> updatePromotion(Promotions promotions) {
        var promotionUpdate = promotionRepository.findById(promotions.getId());
        if(promotionUpdate.isEmpty()){
            return  promotionReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"promotion not found",null);
        }
        promotionUpdate.get().setName(promotions.getName());
        promotionUpdate.get().setIsActive(promotions.getIsActive());
        promotionUpdate.get().setEndTime(promotions.getEndTime());
        promotionUpdate.get().setDescsription(promotions.getDescsription());
        promotionUpdate.get().setPercent(promotions.getPercent());
        promotionUpdate.get().setQuantity(promotions.getQuantity());
        promotionUpdate.get().setStartTime(promotions.getStartTime());
        promotionUpdate.get().setRankCustomerId(promotions.getRankCustomerId());
        promotionUpdate.get().setType(promotions.getType());
        promotionRepository.save(promotionUpdate.get());
        return promotionReponse.reponseSuccess("update promotion successfully",promotionConverter.promotionDto(promotionUpdate.get()));
    }

    @Override
    public ReponseObject<PromotionDto> deletePromotion(int id) {
        var promotion = promotionRepository.findById(id);
        if(promotion.isEmpty()){
            return promotionReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"promotion not found",null);
        }
        promotionRepository.delete(promotion.get());
        return promotionReponse.reponseSuccess("delete promotion successfully",promotionConverter.promotionDto(promotion.get()));
    }

    @Override
    public ReponseObject<PromotionDto> getPromotionById(int id) {
        var promotion = promotionRepository.findById(id);
        if(promotion.isEmpty()){
            return promotionReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"promotion not found",null);
        }

        return promotionReponse.reponseSuccess("delete promotion successfully",promotionConverter.promotionDto(promotion.get()));

    }
}
