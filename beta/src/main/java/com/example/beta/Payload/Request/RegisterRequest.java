package com.example.beta.Payload.Request;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class RegisterRequest {
    public Boolean isActive;
    private String userName;
    private String password;
    private String email;
    private String phoneNumber;
    private String name;
    private int point;
    private int roleId;
    private int rankCustomerId;
    private int userStatusId;


}
