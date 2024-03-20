package com.example.beta.Payload.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserDto {
    private int id;
    private String userName;
    private String password;
    private String email;
    private String name;
    private String phoneNumber;
}

