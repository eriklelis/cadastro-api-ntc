package com.hoteis.api_cadastro.clients.keycloak.response;

import lombok.Data;

@Data
public class RoleResponse {
    private String id;
    private String name;
    private String description;
    private Boolean composite;
    private Boolean clientRole;
    private String containerId;
}
