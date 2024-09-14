package com.hoteis.api_cadastro.clients.keycloak.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupMemberResponse {
    private String id;
    private boolean enabled;
    private String username;
    private String email;
}
