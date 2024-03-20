package com.example.beta.Services.Interfaces;

import com.example.beta.Model.UserStatus;
import com.example.beta.Payload.Reponse.ReponseObject;

public interface IUserStatusServices {
    ReponseObject<UserStatus> addUserStatusServices(UserStatus userStatus);
    ReponseObject<UserStatus> updateUserStatusServices(UserStatus userStatus);
    ReponseObject<UserStatus> getUserStatusById(int id);
    ReponseObject<UserStatus> deleteUserStatus(int id);
}
