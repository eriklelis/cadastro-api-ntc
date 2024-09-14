package com.hoteis.api_cadastro.clients.keycloak.request_dto;

import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class CreateClientRequest {
    private String name;
    private String description;
    private String protocol;
    private Boolean publicClient;
    private Boolean bearerOnly;
    private String rootUrl;
    private List<String> redirectUris;
    private List<String> webOrigins;

    public CreateClientRequest(String nome, String description, String protocol, String url) {
        name = nome;
        this.description = description;
        this.protocol = protocol;
        publicClient = false;
        bearerOnly = false;
        rootUrl = url;
        redirectUris = Arrays.asList(url + "*");
        webOrigins = Arrays.asList(url, "*");
    }
}
