package com.example.beta.Services.Interfaces;

import com.example.beta.Model.RankCustomer;
import com.example.beta.Payload.Reponse.ReponseObject;

public interface IRankCustomerServices {
    ReponseObject<RankCustomer> addRankCustomer(RankCustomer rankCustomer);
    ReponseObject<RankCustomer> updateRankCustomer(RankCustomer rankCustomer);

    ReponseObject<RankCustomer> getRankCustomer(int id);
    ReponseObject<RankCustomer> deleteRankCustomer(int id);

}
