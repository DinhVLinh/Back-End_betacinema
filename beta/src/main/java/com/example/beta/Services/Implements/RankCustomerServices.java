package com.example.beta.Services.Implements;

import com.example.beta.Model.RankCustomer;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Repository.RankCustomerRepository;
import com.example.beta.Services.Interfaces.IRankCustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;

@Service
public class RankCustomerServices implements IRankCustomerServices {
    @Autowired
    private RankCustomerRepository rankCustomerRepository;

    @Autowired
    private ReponseObject rankCustomerReponse;
    @Override
    public ReponseObject<RankCustomer> addRankCustomer(RankCustomer rankCustomer){
        RankCustomer rankCustomerNew = RankCustomer.builder()
                .point(rankCustomer.getPoint())
                .description(rankCustomer.getDescription())
                .name(rankCustomer.getName())
                .isActive(rankCustomer.getIsActive())
                .build();
        rankCustomerRepository.save(rankCustomerNew);

        return rankCustomerReponse.reponseSuccess("add rank customer successfully",rankCustomerNew);
    }

    @Override
    public ReponseObject<RankCustomer> updateRankCustomer(RankCustomer rankCustomer) {
        var rankCustomerUpdate = rankCustomerRepository.findById(rankCustomer.getId());
        if(rankCustomerUpdate.isEmpty()){
            return  rankCustomerReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"rank customer not found", null);
        }

        rankCustomerUpdate.get().setDescription(rankCustomer.getDescription());
        rankCustomerUpdate.get().setName(rankCustomer.getName());
        rankCustomerUpdate.get().setPoint(rankCustomer.getPoint());
        rankCustomerUpdate.get().setIsActive(rankCustomer.getIsActive());
        rankCustomerRepository.save(rankCustomerUpdate.get());
        return rankCustomerReponse.reponseSuccess("update rank customer successfully",rankCustomerUpdate.get());
    }

    @Override
    public ReponseObject<RankCustomer> getRankCustomer(int id) {
        var rankCustomer = rankCustomerRepository.findById(id);
        if(rankCustomer.isEmpty()){
            return  rankCustomerReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"rank customer not found", null);
        }

        return rankCustomerReponse.reponseSuccess("update rank customer successfully",rankCustomer.get());
    }

    @Override
    public ReponseObject<RankCustomer> deleteRankCustomer(int id) {
        var rankCustomer = rankCustomerRepository.findById(id);
        if(rankCustomer.isEmpty()){
            return  rankCustomerReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"rank customer not found", null);
        }
        rankCustomerRepository.delete(rankCustomer.get());
        return rankCustomerReponse.reponseSuccess("update rank customer successfully",rankCustomer.get());
    }
}
