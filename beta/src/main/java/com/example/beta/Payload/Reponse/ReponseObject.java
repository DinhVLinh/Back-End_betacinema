package com.example.beta.Payload.Reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReponseObject<T> {
    private int statusCode;
    private String message;
    private T data;



    public  ReponseObject<T> reponseSuccess(String message, T data){
        return new ReponseObject<T>(HttpURLConnection.HTTP_OK, message, data);
    }

    public  ReponseObject<T> reponseError(int statusCode, String message, T data){
        return new ReponseObject<T>(statusCode, message, data);
    }
}
