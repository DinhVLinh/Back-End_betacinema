package com.example.beta.Payload.Converter;

import com.example.beta.Model.User;
import com.example.beta.Payload.DTO.UserDto;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserDto userDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setPhoneNumber(user.getPhoneNumber());
        return  userDto;
    }
}
