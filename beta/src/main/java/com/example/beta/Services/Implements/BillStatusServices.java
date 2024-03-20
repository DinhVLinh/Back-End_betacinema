package com.example.beta.Services.Implements;

import com.example.beta.Model.BillFood;
import com.example.beta.Model.BillStatus;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Repository.BillFoodRepository;
import com.example.beta.Repository.BillStatusRepository;
import com.example.beta.Services.Interfaces.IBillFoodServices;
import com.example.beta.Services.Interfaces.IBillStatusServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;

@Service
public class BillStatusServices implements IBillStatusServices {
    @Autowired
    private BillStatusRepository billStatusRepository;
    @Autowired
    private ReponseObject billStatusReponse;

    @Override
    public ReponseObject<BillStatus> addBillStatus(BillStatus billStatus) {
        BillStatus billStatusNew = BillStatus.builder()
                .name(billStatus.getName())
                .build();
        billStatusRepository.save(billStatusNew);
        return billStatusReponse.reponseSuccess("add bill status successfully",billStatusNew);
    }

    @Override
    public ReponseObject<BillStatus> updateBillStatus(BillStatus billStatus) {
        var billStatusUpdate = billStatusRepository.findById(billStatus.getId());
        if(billStatusUpdate.isEmpty()){
            return billStatusReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"bill status not found",null);
        }
        billStatusUpdate.get().setName(billStatus.getName());
        billStatusRepository.save(billStatusUpdate.get());
        return billStatusReponse.reponseSuccess("update bill status successfully",billStatusUpdate.get());
    }

    @Override
    public ReponseObject<BillStatus> getBillStatusById(int id) {
        var billStatus = billStatusRepository.findById(id);
        if(billStatus.isEmpty()){
            return billStatusReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"bill status not found",null);
        }

        return billStatusReponse.reponseSuccess("update bill status successfully",billStatus.get());
    }

    @Override
    public ReponseObject<BillStatus> deleteBillStatusById(int id) {
        var billStatus = billStatusRepository.findById(id);
        if(billStatus.isEmpty()){
            return billStatusReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"bill status not found",null);
        }
        billStatusRepository.delete(billStatus.get());
        return billStatusReponse.reponseSuccess("update bill status successfully",billStatus.get());
    }
}
