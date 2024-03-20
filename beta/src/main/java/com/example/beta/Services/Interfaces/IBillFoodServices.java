package com.example.beta.Services.Interfaces;

import com.example.beta.Model.BillFood;
import com.example.beta.Payload.Reponse.ReponseObject;

public interface IBillFoodServices {
    ReponseObject<BillFood> addBillFood(BillFood billFood);
    ReponseObject<BillFood> updateBillFood(BillFood billFood);
    ReponseObject<BillFood> getBillFoodById(int id);
    ReponseObject<BillFood> deleteBillFood(int id);


}
