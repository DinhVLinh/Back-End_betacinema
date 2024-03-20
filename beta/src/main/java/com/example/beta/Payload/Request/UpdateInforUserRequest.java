package com.example.beta.Payload.Request;

import lombok.Data;

@Data
public class UpdateInforUserRequest {
    private int userId;
    private String email;
    private String phoneNumber;
    private String name;
    private int point;
}
