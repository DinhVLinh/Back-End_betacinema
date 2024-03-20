package com.example.beta.Payload.Converter;

import com.example.beta.Model.Bill;
import com.example.beta.Payload.DTO.BillDto;
import org.springframework.stereotype.Component;

@Component
public class BillConverter {
    public BillDto billDto(Bill bill){
        BillDto billDto = new BillDto();
        billDto.setActive(bill.getIsActive());
        billDto.setName(bill.getName());
        billDto.setUpdateTime(bill.getUpdateTime());
        billDto.setTradingCode(bill.getTradingCode());
        billDto.setCreateTime(bill.getCreateTime());
        billDto.setTotalMoney(bill.getTotalMoney());

        return billDto;
    }
}
