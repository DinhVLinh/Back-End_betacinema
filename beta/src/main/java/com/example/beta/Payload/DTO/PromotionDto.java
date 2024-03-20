package com.example.beta.Payload.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PromotionDto {
    private int percent;
    private int quantity;
    private String type;
    private LocalDate startTime;
    private LocalDate endTime;
    private String description;
    private String name;
    private boolean isActive;
}
