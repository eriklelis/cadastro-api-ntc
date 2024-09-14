package com.hoteis.api_cadastro.clients.keycloak.response;

import lombok.Data;

@Data
public class SecretResponse {
    private String type;
    private String value;
}
