package com.hoteis.api_cadastro.clients.keycloak.request_dto;

import lombok.Data;

@Data
public class CredentialRepresentationRequest {
    private String type;
    private String value;
    private boolean temporary;

    public CredentialRepresentationRequest(String type, String value, boolean temporary) {
        this.temporary = temporary;
        this.type = type;
        this.value = value;
    }
}
