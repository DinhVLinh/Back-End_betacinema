package com.example.beta.Payload.Request;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String userName;
    private String password;
}
