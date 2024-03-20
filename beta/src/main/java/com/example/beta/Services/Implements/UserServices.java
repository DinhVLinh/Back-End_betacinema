package com.example.beta.Services.Implements;

import com.example.beta.Handle.Validate;
import com.example.beta.Payload.Converter.UserConverter;
import com.example.beta.Payload.DTO.UserDto;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Payload.Request.UpdateInforUserRequest;
import com.example.beta.Repository.UserRepository;
import com.example.beta.Services.Interfaces.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;

@Service
public class UserServices implements IUserServices {
    @Autowired
    UserRepository userRepo;

    @Autowired
    UserConverter userConverter;
    @Autowired
    private ReponseObject<UserDto> userDtoReponseObject;
    @Override
    public Page<UserDto> findAllUser(int page , int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return userRepo.findAll(pageRequest).map(x -> userConverter.userDto(x));
    }

    @Override
    public ReponseObject<UserDto> findUserById(int id) {
        var user = userRepo.findById(id);
        if(user.isEmpty()){
            return userDtoReponseObject.reponseError(HttpURLConnection.HTTP_BAD_REQUEST, "khong tim thay nguoi dung",null);
        }
        return userDtoReponseObject.reponseSuccess("lay nguoi dung thanh cong" , userConverter.userDto(user.get()));
    }

    @Override
    public ReponseObject<UserDto> deleteUserById(int id) {
        var user = userRepo.findById(id);
        if(user.isEmpty()){
            return userDtoReponseObject.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"khong tim thay user", null);
        }

        userRepo.delete(user.get());
        return userDtoReponseObject.reponseSuccess("xoa user thanh cong", userConverter.userDto(user.get()));
    }

    @Override
    public ReponseObject<UserDto> updateInforUser(UpdateInforUserRequest request) {
        var user = userRepo.findById(request.getUserId());
        if(user.isEmpty()){
            return userDtoReponseObject.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"nguoi dung khong ton tai",null);
        }
        user.get().setName(request.getName());
        if(!Validate.isValidPhoneNumber(request.getPhoneNumber())){
            return userDtoReponseObject.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"dinh dang so dien thoai khong dung", null);
        }
        user.get().setPhoneNumber(request.getPhoneNumber());
        if(!Validate.isValidEmail(request.getEmail())){
            return userDtoReponseObject.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"dinh dang email khong dung",null);
        }
        user.get().setEmail(request.getEmail());
        user.get().setPoint(request.getPoint());
        userRepo.save(user.get());

        return userDtoReponseObject.reponseSuccess("sua thong tin nguoi dung thanh cong", userConverter.userDto(user.get()));
    }


}
