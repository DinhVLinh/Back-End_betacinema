package com.example.beta.Services.Implements;

import com.example.beta.Handle.SendEmail;
import com.example.beta.Handle.Validate;
import com.example.beta.Model.ConfirmEmail;
import com.example.beta.Model.User;
import com.example.beta.Payload.Converter.UserConverter;
import com.example.beta.Payload.DTO.UserDto;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Payload.Reponse.AuthenticationReponse;
import com.example.beta.Payload.Request.*;
import com.example.beta.Repository.ConfirmEmailRepo;
import com.example.beta.Repository.RefeshTokenRepository;
import com.example.beta.Repository.RoleRepository;

import com.example.beta.Repository.UserRepository;
import com.example.beta.Services.Interfaces.IAuthServices;

import com.example.beta.Ultis.JwtAuthProvider;
import com.example.beta.Ultis.JwtServices;


import com.example.beta.Ultis.UserCustomDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Random;


@Service
public class AuthServices implements IAuthServices {
    @Autowired
    private ReponseObject<UserDto> userDtoReponseObject;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private JwtServices jwtServices;
    @Autowired
    private JwtAuthProvider jwtAuthProvider;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private ConfirmEmailRepo confirmEmailRepo;

    @Autowired
    private RefeshTokenRepository refeshTokenRepository;



    @Override
    public ReponseObject<UserDto> register( RegisterRequest request) {
        if (request.getEmail() == null || request.getPhoneNumber() == null || request.getUserName() == null || request.getPassword() == null) {
            return userDtoReponseObject.reponseError(
                    HttpURLConnection.HTTP_BAD_REQUEST,
                    "vui long dien thong tin day du", null);
        }

        if (!Validate.isValidEmail(request.getEmail())) {
            return userDtoReponseObject.reponseError(
                    HttpURLConnection.HTTP_BAD_REQUEST,
                    "dinh dang email khong hop le", null);
        }

        if (!Validate.isValidPhoneNumber((request.getPhoneNumber()))) {
            return userDtoReponseObject.reponseError(
                    HttpURLConnection.HTTP_BAD_REQUEST,
                    "so dien thoai khong hop le", null);
        }
        var checkUserName = userRepo.findAll().stream().filter(x -> x.getUserName().equals(request.getUserName())).findFirst().orElse(null);
        var checkEmail = userRepo.findAll().stream().filter(x -> x.getEmail().equals(request.getEmail())).findFirst().orElse(null);
        if (checkUserName != null) {
            return userDtoReponseObject.reponseError(HttpURLConnection.HTTP_BAD_REQUEST, "ten tai khoan da ton tai", null);
        }
        if (checkEmail != null) {
            return userDtoReponseObject.reponseError(HttpURLConnection.HTTP_BAD_REQUEST, "email da ton tai", null);
        }
        var role = roleRepo.findById(1);
        User user = User.builder()
                .userName(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .name(request.getName())
                .point(request.getPoint())
                .role(role.get())
                .rankCustomerId(request.getRankCustomerId())
                .userStatusId(request.getUserStatusId())
                .isActive(request.getIsActive())
                .roleId(request.getRoleId()).build();
        UserCustomDetails userCustomDetails = new UserCustomDetails(user);
        var userSaved = userRepo.save(user);
       var jwtToken = jwtServices.generateToken(userCustomDetails);
        var tokenRefesh = jwtServices.generateRefreshToken(userCustomDetails);
        var refeshToken = refeshTokenRepository.findById(user.getId());
        refeshToken.get().setToken(tokenRefesh);

        return userDtoReponseObject.reponseSuccess("dang ki thanh cong", userConverter.userDto(user));
    }

    @Override
    public AuthenticationReponse login(LoginRequest request) {
        try {
            var user = userRepo.findByUserName(request.getUserName());

            if (user.isPresent()) {


                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.get().getUserName(), request.getPassword());
                jwtAuthProvider.authenticate(token);
                UserCustomDetails userCustomDetails = new UserCustomDetails(user.get());
                String jwtToken = jwtServices.generateToken(userCustomDetails);
                String jwtRefeshToken = jwtServices.generateRefreshToken(userCustomDetails);
                return AuthenticationReponse.builder().accessToken(jwtToken).refeshToken(jwtRefeshToken).build();
            } else {
                throw new IllegalArgumentException("Không tìm thấy người dùng");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AuthenticationReponse.builder().accessToken("ERROR").build();
        }
    }
    @Override
    public String changePassword(int id, ChangePasswordRequest request) {
        var user = userRepo.findById(id);
        if (user.isEmpty()) {
            return "nguoi dung khong ton tai";
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(user.get().getPassword(), request.getOldPassword())) {
            return "mat khau cu khong chinh xac";
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            return "xac nhan mat khau khong chinh xac";
        }

        user.get().setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepo.save(user.get());
        return "doi mat khau thanh cong";
    }

    @Override
    public String forgotPassword( ForgotPassword request) {
        var user = userRepo.findByEmail(request.getEmail());
        try {
            if (user.isEmpty()) {
                return "email" + request.getEmail() + "nguoi dung khong co tren he thong";
            }
            var confirmEmail = confirmEmailRepo.findAll().stream().filter(x -> x.getUserId() == user.get().getId()).findFirst();

            if (confirmEmail.isPresent()) {
                confirmEmailRepo.delete(confirmEmail.get());
                confirmEmailRepo.save(confirmEmail.get());
            }
            ConfirmEmail confirm = ConfirmEmail
                    .builder()
                    .userId(user.get().getId())
                    .isConfirm(false)
                    .requiredTime(LocalDate.now())
                    .expiredTime(LocalDateTime.now().plusHours(1).toLocalDate())
                    .confirmCode(codeRandom())
                    .build();
            confirmEmailRepo.save(confirm);

            SendEmail sendEmail = SendEmail.builder()
                    .toEmail(request.getEmail())
                    .tieuDe("Nhận mã xác nhận để tạo mật khẩu mới từ đây: ")
                    .noiDung("Ma kich hoat cua ban la " + confirm.getConfirmCode() + "ma nay se het han sau 1h")
                    .build();
            return SendToEmail(sendEmail);
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }

    }

    public String SendToEmail( SendEmail sendMail) {
        if (!Validate.isValidEmail(sendMail.getToEmail())) {
            return "Định dạng email " + sendMail.getToEmail() + " không đúng định dạng";
        }
        final String userName = "linhcuto@gmail.com";
        final String password = "xjoh gnlf mdkx zbno";
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userName));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(sendMail.getToEmail())
            );
            message.setSubject(sendMail.getTieuDe());
            message.setText(sendMail.getNoiDung());
            message.setContent(sendMail.getNoiDung(), "text/html; charset=utf-8");
            Transport.send(message);
            return "Xác nhận gửi email thành công, lấy mã xác thực";
        } catch (MessagingException e) {
            return "Lỗi khi gửi mail: " + e.getMessage();
        }
    }

    private  String codeRandom() {
        Random random = new Random();
        return String.valueOf(random.nextInt(900000) + 10000);
    }

}

