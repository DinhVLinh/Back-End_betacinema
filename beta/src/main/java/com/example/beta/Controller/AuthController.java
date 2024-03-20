package com.example.beta.Controller;

import com.example.beta.Payload.DTO.UserDto;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Payload.Reponse.AuthenticationReponse;
import com.example.beta.Payload.Request.ChangePasswordRequest;
import com.example.beta.Payload.Request.ForgotPassword;
import com.example.beta.Payload.Request.LoginRequest;
import com.example.beta.Payload.Request.RegisterRequest;
import com.example.beta.Repository.UserRepository;
import com.example.beta.Services.Implements.AuthServices;
import com.example.beta.Ultis.UserCustomDetails;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthServices _services;
    @Autowired
    private UserRepository userRepo;


    @GetMapping("/test")
    private String test(){
        return "test";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    private ReponseObject<UserDto> register(@RequestBody RegisterRequest request) {
        return _services.register(request);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    private AuthenticationReponse login(@RequestBody LoginRequest request) {
        var result = _services.login(request);

        try {
            if (result == null) {
                throw new IllegalArgumentException("Không hợp lệ");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @PutMapping(value = "/changepassword")
    private ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
        try {
            UserCustomDetails user = (UserCustomDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (userRepo.findById(user.getUser().getId()).isPresent()) {
                System.out.println("Nguoi dung la: " + user.getUser().getUserName());
                _services.changePassword(user.getUser().getId(),request);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Không tìm thấy thông tin người dùng.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok("Đổi mật khẩu thành công");
    }

    @PostMapping("/forgotpassword")
    private String forgotPassword(@RequestBody ForgotPassword request){
        var result = _services.forgotPassword(request);
        try {
            if (result == null){
                throw  new IllegalArgumentException("khong hop le");

            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return  result;
    }

//    @PostMapping("/refresh-token")
//    public void refreshToken(
//            HttpServletRequest request,
//            HttpServletResponse response
//    ) throws IOException, java.io.IOException {
//        _services.refeshToken(request, response);
//    }
}
