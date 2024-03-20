package com.example.beta.Services.Interfaces;

import com.example.beta.Model.Bill;
import com.example.beta.Payload.DTO.BillDto;
import com.example.beta.Payload.Reponse.ReponseObject;

public interface IBillServices {
ReponseObject<BillDto> addBill(Bill bill);
ReponseObject<BillDto> updateBill(Bill bill);
ReponseObject<BillDto> deleteBill(int id);
ReponseObject<BillDto> getBillById(int id);
}
