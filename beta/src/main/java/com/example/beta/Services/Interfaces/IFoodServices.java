package com.example.beta.Services.Interfaces;

import com.example.beta.Model.Food;
import com.example.beta.Model.Movies;
import com.example.beta.Payload.Reponse.ReponseObject;

public interface IFoodServices {
    ReponseObject<Food> addFood(Food food);
    ReponseObject<Food> updateFood(Food food);

    ReponseObject<Food> deleteFood(int id);
    ReponseObject<Food> getFoodById(int id);

}
