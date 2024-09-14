package com.hoteis.api_cadastro.clients.keycloak.request_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class GroupsRequest {
    private String name;
}
