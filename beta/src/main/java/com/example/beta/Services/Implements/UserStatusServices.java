package com.example.beta.Services.Implements;

import com.example.beta.Model.User;
import com.example.beta.Model.UserStatus;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Repository.UserStatusRepository;
import com.example.beta.Services.Interfaces.IUserStatusServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;

@Service
public class UserStatusServices implements IUserStatusServices {

    @Autowired
    private UserStatusRepository userStatusRepository;
    @Autowired
    private ReponseObject userStatusReponse;
    @Override
    public ReponseObject<UserStatus> addUserStatusServices(UserStatus userStatus) {
        UserStatus userStatusNew = UserStatus.builder()
                .code(userStatus.getCode())
                .name(userStatus.getName())
                .build();
        userStatusRepository.save(userStatusNew);
        return userStatusReponse.reponseSuccess("add user status successfully",userStatusNew);
    }

    @Override
    public ReponseObject<UserStatus> updateUserStatusServices(UserStatus userStatus) {
        var userStatusUpdate = userStatusRepository.findById(userStatus.getId());
        if(userStatusUpdate.isEmpty()){
            return userStatusReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"user status not found",null);
        }
        userStatusUpdate.get().setCode(userStatus.getCode());
        userStatusUpdate.get().setName(userStatus.getName());
        return userStatusReponse.reponseSuccess("update user status successfully",userStatusUpdate.get());
    }

    @Override
    public ReponseObject<UserStatus> getUserStatusById(int id) {
        var userStatus = userStatusRepository.findById(id);
        if(userStatus.isEmpty()){
            return userStatusReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"user status not found",null);
        }

        return userStatusReponse.reponseSuccess("get user by id status successfully",userStatus.get());
    }

    @Override
    public ReponseObject<UserStatus> deleteUserStatus(int id) {
        var userStatus = userStatusRepository.findById(id);
        if(userStatus.isEmpty()){
            return userStatusReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"user status not found",null);
        }
        userStatusRepository.delete(userStatus.get());
        return userStatusReponse.reponseSuccess("delete user status successfully",userStatus.get());
    }
}
