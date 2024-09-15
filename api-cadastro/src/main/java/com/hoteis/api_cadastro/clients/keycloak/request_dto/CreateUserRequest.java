package com.hoteis.api_cadastro.clients.keycloak.request_dto;

import java.util.ArrayList;
import java.util.List;

import com.hoteis.api_cadastro.dto.usuario.CriacaoUsuarioRequestDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserRequest {
    private String id;
    private String username;
    private String email;
    private boolean enabled;
    private List<CredentialRepresentationRequest> credentials = new ArrayList<>();
    private List<String> groups = new ArrayList<>();
    private String firstName;
    private String lastName;

    public CreateUserRequest(CriacaoUsuarioRequestDTO usuarioDTO, String group) {
        this.username = usuarioDTO.getUsername();
        this.email = usuarioDTO.getEmail();
        this.groups.add(group);
        this.enabled = true;
        this.credentials.add(new CredentialRepresentationRequest("password", usuarioDTO.getSenha(), false));
    }
}
