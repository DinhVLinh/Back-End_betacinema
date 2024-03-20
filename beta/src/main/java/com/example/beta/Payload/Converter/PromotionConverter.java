package com.example.beta.Payload.Converter;

import com.example.beta.Model.Promotions;
import com.example.beta.Payload.DTO.PromotionDto;
import org.springframework.stereotype.Component;

@Component
public class PromotionConverter {
    public PromotionDto promotionDto(Promotions promotion){
        PromotionDto promotionDto = new PromotionDto();
        promotionDto.setActive(promotion.getIsActive());
        promotionDto.setName(promotionDto.getName());
        promotionDto.setPercent(promotionDto.getPercent());
        promotionDto.setQuantity(promotion.getQuantity());
        promotionDto.setEndTime(promotion.getEndTime());
        promotionDto.setStartTime(promotion.getStartTime());
        promotionDto.setDescription(promotionDto.getDescription());
        return promotionDto;
    }
}
