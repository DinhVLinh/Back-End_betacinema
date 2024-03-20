package com.example.beta.Payload.Reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationReponse {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refesh_token")
    private String refeshToken;

}
