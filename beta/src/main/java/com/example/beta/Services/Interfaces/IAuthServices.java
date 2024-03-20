package com.example.beta.Services.Interfaces;

import com.example.beta.Payload.DTO.UserDto;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Payload.Reponse.AuthenticationReponse;
import com.example.beta.Payload.Request.ChangePasswordRequest;
import com.example.beta.Payload.Request.ForgotPassword;
import com.example.beta.Payload.Request.LoginRequest;
import com.example.beta.Payload.Request.RegisterRequest;

public interface IAuthServices {
    ReponseObject<UserDto> register(RegisterRequest request);
    AuthenticationReponse login(LoginRequest request);

    String changePassword(int id ,ChangePasswordRequest request);

    String forgotPassword(ForgotPassword request);
}
