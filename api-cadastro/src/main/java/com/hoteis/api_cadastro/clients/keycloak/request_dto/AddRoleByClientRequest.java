package com.hoteis.api_cadastro.clients.keycloak.request_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AddRoleByClientRequest {
    private String id;
    private String containerId;
    private String name;
    private boolean composite = true;
    private boolean clientRole = true;

    public AddRoleByClientRequest(String id, String containerId, String name) {
        this.id = id;
        this.containerId = containerId;
        this.name = name;
    }
}
