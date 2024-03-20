package com.example.beta.Services.Interfaces;

import com.example.beta.Model.BillStatus;
import com.example.beta.Payload.Reponse.ReponseObject;

public interface IBillStatusServices {
    ReponseObject<BillStatus> addBillStatus(BillStatus billStatus);
    ReponseObject<BillStatus> updateBillStatus(BillStatus billStatus);
    ReponseObject<BillStatus> getBillStatusById(int id);
    ReponseObject<BillStatus> deleteBillStatusById(int id);

}
