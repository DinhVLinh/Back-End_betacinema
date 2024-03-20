package com.example.beta.Payload.DTO;

import lombok.Data;

import java.io.Serializable;
@Data
public class PaymentDto implements Serializable {
    private String status;
    private String messsage;
    private String URL;
}
