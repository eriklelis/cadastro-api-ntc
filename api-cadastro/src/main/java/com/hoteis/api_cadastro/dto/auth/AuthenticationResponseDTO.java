package com.hoteis.api_cadastro.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
public class AuthenticationResponseDTO {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private String expires;

    @Builder
    public AuthenticationResponseDTO(String accessToken, String expires) {
        this.accessToken = accessToken;
        this.expires = expires;
    }
}
