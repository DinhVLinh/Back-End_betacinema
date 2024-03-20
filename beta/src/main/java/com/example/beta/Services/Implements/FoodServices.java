package com.example.beta.Services.Implements;

import com.example.beta.Model.Food;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Repository.FoodRepository;
import com.example.beta.Services.Interfaces.IFoodServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;

@Service
public class FoodServices implements IFoodServices {
    @Autowired
    private FoodRepository foodRepo;
    @Autowired
    private ReponseObject foodReponse;
    @Override
    public ReponseObject<Food> addFood(Food food) {
        Food foodNew = Food.builder()
                .price(food.getPrice())
                .desccription(food.getDesccription())
                .image(food.getImage())
                .nameOfFood(food.getNameOfFood())
                .isActive(food.getIsActive())
                .build();
        foodRepo.save(foodNew);
        return foodReponse.reponseSuccess("add food successfully" , foodNew);
    }

    @Override
    public ReponseObject<Food> updateFood(Food food) {
        var foodUpdate = foodRepo.findById(food.getId());
        if(foodUpdate.isEmpty()){
            return foodReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"not found",null);
        }
        foodUpdate.get().setBillFoods(food.getBillFoods());
        foodUpdate.get().setNameOfFood(food.getNameOfFood());
        foodUpdate.get().setIsActive(food.getIsActive());
        foodUpdate.get().setImage(food.getImage());
        foodUpdate.get().setDesccription(food.getDesccription());
        foodUpdate.get().setPrice(food.getPrice());
        foodRepo.save(foodUpdate.get());
        return foodReponse.reponseSuccess("update food successfully",foodUpdate.get());
    }

    @Override
    public ReponseObject<Food> deleteFood(int id) {
        var food = foodRepo.findById(id);
        if(food.isEmpty()){
            return foodReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"not found",null);
        }
        foodRepo.delete(food.get());
        return foodReponse.reponseSuccess("delete food successfully",food.get());
    }

    @Override
    public ReponseObject<Food> getFoodById(int id) {
        var food = foodRepo.findById(id);
        if(food.isEmpty()){
            return foodReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"food not found",null);
        }
        return foodReponse.reponseSuccess("get food successfully",food.get());
    }
}
