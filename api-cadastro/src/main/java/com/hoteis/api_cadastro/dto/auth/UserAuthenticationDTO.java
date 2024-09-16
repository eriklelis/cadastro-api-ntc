package com.hoteis.api_cadastro.dto.auth;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SecondaryRow;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthenticationDTO {
    private String clientId;
    private String clientSecret;
    private String username;
    private String password;
}
