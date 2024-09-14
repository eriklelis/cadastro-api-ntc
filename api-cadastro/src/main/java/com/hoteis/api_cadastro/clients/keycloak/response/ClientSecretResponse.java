package com.hoteis.api_cadastro.clients.keycloak.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientSecretResponse {
    private String clientId;
    private String secret;
}
