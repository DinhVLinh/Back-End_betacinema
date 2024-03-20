package com.example.beta.Payload.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ScheduleDto {
    private double price;
    private LocalDate startAt;
    private LocalDate endAt;
    private String code;
    private String name;
    private boolean isActive;
}
