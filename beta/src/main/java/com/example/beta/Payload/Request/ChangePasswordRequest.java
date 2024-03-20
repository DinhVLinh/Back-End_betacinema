package com.example.beta.Payload.Request;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
