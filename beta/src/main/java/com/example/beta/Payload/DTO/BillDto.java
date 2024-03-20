package com.example.beta.Payload.DTO;

import lombok.Data;

import java.time.LocalDate;
@Data
public class BillDto {
    private double totalMoney;
    private String tradingCode;
    private LocalDate createTime;
    private LocalDate updateTime;
    private String name;
    private boolean isActive;
}
