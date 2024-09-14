package com.hoteis.api_cadastro.clients.keycloak.request_dto;

import lombok.Data;

@Data
public class CreateRolesRequest {
    private String name;

    public CreateRolesRequest(String name) {
        this.name = name;
    }
}
