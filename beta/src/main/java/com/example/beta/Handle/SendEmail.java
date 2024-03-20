package com.example.beta.Handle;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.WithBy;

@Data
@Builder
public class SendEmail {

    private String toEmail;
    private String tieuDe;
    private String noiDung;
}
