package com.example.beta.Services.Interfaces;

import com.example.beta.Payload.DTO.UserDto;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Payload.Request.UpdateInforUserRequest;
import org.springframework.data.domain.Page;

public interface IUserServices {
    Page<UserDto> findAllUser(int page , int size);
    ReponseObject<UserDto> findUserById(int id);

    ReponseObject<UserDto> deleteUserById(int id);

    ReponseObject<UserDto> updateInforUser(UpdateInforUserRequest request);
}
