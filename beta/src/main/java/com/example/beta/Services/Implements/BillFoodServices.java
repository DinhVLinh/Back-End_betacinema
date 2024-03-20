package com.example.beta.Services.Implements;

import com.example.beta.Model.BillFood;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Repository.BillFoodRepository;
import com.example.beta.Repository.BillTicketRepository;
import com.example.beta.Services.Interfaces.IBillFoodServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;

@Service
public class BillFoodServices implements IBillFoodServices {

    @Autowired
    private BillFoodRepository billFoodRepository;
    @Autowired
    private ReponseObject billFoodReponse;
    @Override
    public ReponseObject<BillFood> addBillFood(BillFood billFood) {
        BillFood billFoodNew = BillFood.builder()
                .quantity(billFood.getQuantity())
                .billId(billFood.getBillId())
                .foodId(billFood.getFoodId())
                .build();
        billFoodRepository.save(billFoodNew);
        return billFoodReponse.reponseSuccess("add bill food successfully",billFoodNew);
    }

    @Override
    public ReponseObject<BillFood> updateBillFood(BillFood billFood) {
        var billFoodUpdate = billFoodRepository.findById(billFood.getId());
        if(billFoodUpdate.isEmpty()){
            return billFoodReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"bill food not found",null);
        }
        billFoodUpdate.get().setBillId(billFood.getBillId());
        billFoodUpdate.get().setQuantity(billFood.getQuantity());
        billFoodUpdate.get().setFoodId(billFood.getFoodId());
        billFoodRepository.save(billFoodUpdate.get());
        return billFoodReponse.reponseSuccess("update bill food successfully",billFoodUpdate.get());
    }

    @Override
    public ReponseObject<BillFood> getBillFoodById(int id) {
        var billFood = billFoodRepository.findById(id);
        if(billFood.isEmpty()){
            return billFoodReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"bill food not found",null);
        }

        return billFoodReponse.reponseSuccess("get bill food successfully",billFood.get());
    }

    @Override
    public ReponseObject<BillFood> deleteBillFood(int id) {
        var billFood = billFoodRepository.findById(id);
        if(billFood.isEmpty()){
            return billFoodReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"bill food not found",null);
        }
        billFoodRepository.delete(billFood.get());
        return billFoodReponse.reponseSuccess("get bill food successfully",billFood.get());
    }
}
