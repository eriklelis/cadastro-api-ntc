package com.hoteis.api_cadastro.service.impl;

import com.hoteis.api_cadastro.clients.keycloak.KeycloakClientAuthenticationService;
import com.hoteis.api_cadastro.dto.auth.AuthenticationResponseDTO;
import com.hoteis.api_cadastro.dto.auth.UserAuthenticationDTO;
import com.hoteis.api_cadastro.exception.NaoAutorizadoException;
import com.hoteis.api_cadastro.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final String KEYCLOAK_USER = "username=";
    private static final String KEYCLOAK_PWD = "&password=";
    private static final String CLIENT_ID = "&client_id=";
    private static final String CLIENT_SECRET = "&client_secret=";
    private static final String SCOPE = "&scope=openid";
    private static final String GRANT_TYPE = "&grant_type=password";

    private final KeycloakClientAuthenticationService keycloakClientAuthenticationService;

    @Override
    public AuthenticationResponseDTO authenticateReturnToken(UserAuthenticationDTO userAuthenticationDTO) {
        try {
            var response = keycloakClientAuthenticationService.auth(getParametersToLogin(userAuthenticationDTO));
            return AuthenticationResponseDTO.builder()
                .accessToken(response.getAccessToken())
                .expires(response.getExpiresIn())
                .build();
        } catch (Exception e) {
            throw new NaoAutorizadoException();
        }
    }

    private byte[] getParametersToLogin(UserAuthenticationDTO request) {
        var builder = new StringBuilder();
        builder.append(KEYCLOAK_USER).append(request.getUsername());
        builder.append(KEYCLOAK_PWD).append(request.getPassword());
        builder.append(CLIENT_ID).append(request.getClientId());
        builder.append(CLIENT_SECRET).append(request.getClientSecret());
        builder.append(GRANT_TYPE);
        builder.append(SCOPE);
        return builder.toString().getBytes(StandardCharsets.UTF_8);
    }
}
