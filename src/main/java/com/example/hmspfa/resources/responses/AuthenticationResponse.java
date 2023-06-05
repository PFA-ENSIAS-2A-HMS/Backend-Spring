package com.example.hmspfa.resources.responses;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private Long id;
    @JsonProperty(namespace = "access_token")
    private String accessToken;

    @JsonProperty(namespace = "refresh_token")
    private String refreshToken;

    private String role;
}
