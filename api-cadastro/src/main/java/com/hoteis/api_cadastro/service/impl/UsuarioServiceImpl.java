package com.hoteis.api_cadastro.service.impl;

import com.hoteis.api_cadastro.clients.keycloak.KeycloakService;
import com.hoteis.api_cadastro.clients.keycloak.request_dto.AddRoleByClientRequest;
import com.hoteis.api_cadastro.clients.keycloak.request_dto.CreateClientRequest;
import com.hoteis.api_cadastro.clients.keycloak.request_dto.CreateUserRequest;
import com.hoteis.api_cadastro.clients.keycloak.request_dto.GroupsRequest;
import com.hoteis.api_cadastro.clients.keycloak.response.ClientSecretResponse;
import com.hoteis.api_cadastro.clients.keycloak.response.SecretResponse;
import com.hoteis.api_cadastro.domain.Usuario;
import com.hoteis.api_cadastro.dto.CriacaoUsuarioRequestDTO;
import com.hoteis.api_cadastro.dto.CriacaoUsuarioResponseDTO;
import com.hoteis.api_cadastro.exception.ValidacaoException;
import com.hoteis.api_cadastro.repository.UsuarioRepository;
import com.hoteis.api_cadastro.service.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private static final String DESCRIPTION = "OpenID Connect built-in scope: offline_access";
    private static final String PROTOCOL = "openid-connect";
    private static final String LOCATION = "location";
    private static final String CLIENTS_PATH = "/clients/";

    @Value("${cadatros-api.keycloak.root-url}")
    private String url;
    @Value("${cadatros-api.keycloak.roles-client-id}")
    private String containerId;
    @Value("${cadatros-api.keycloak.role-name-adm}")
    private String roleNameADM;
    @Value("${cadatros-api.keycloak.collection-role-adm}")
    private String collectionRoleADM;

    private final UsuarioRepository usuarioRepository;
    private final KeycloakService keycloakService;

    @Override
    @Transactional
    public CriacaoUsuarioResponseDTO criarUsuario(CriacaoUsuarioRequestDTO criacaoUsuarioRequestDTO) {
        credenciaisDisponiveisOuFalha(criacaoUsuarioRequestDTO.getUsername(), criacaoUsuarioRequestDTO.getEmail());
        var usuarioDB = criarUsuarioDB(Usuario.builder().username(criacaoUsuarioRequestDTO.getUsername()).build());
        var usuarioResposta = criarUsuarioKeyCloak(criacaoUsuarioRequestDTO, collectionRoleADM, roleNameADM);
        usuarioResposta.setGuid(usuarioDB.getGuid());
        usuarioResposta.setUsername(usuarioDB.getUsername());
        return usuarioResposta;
    }

    private Usuario criarUsuarioDB(Usuario usuario) {
        return save(usuario);
    }

    private CriacaoUsuarioResponseDTO criarUsuarioKeyCloak(CriacaoUsuarioRequestDTO criacaoUsuarioRequestDTO, String collectionRole, String roleName) {
        var clientId = UUID.randomUUID().toString();
        var clientIdClientSecret = createClientKeycloak(criacaoUsuarioRequestDTO.getUsername());
        createGroup(clientIdClientSecret.getClientId());
        var response = keycloakService.createUser(new CreateUserRequest(criacaoUsuarioRequestDTO, clientId));
        var credencial = Arrays.asList(new AddRoleByClientRequest(collectionRole, containerId, roleName));
        keycloakService.addRoleByClientInUser(containerId, getUserId(response), credencial);
        return CriacaoUsuarioResponseDTO.builder()
                                        .clientId(clientIdClientSecret.getClientId())
                                        .clientSecret(clientIdClientSecret.getSecret())
                                        .build();
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void credenciaisDisponiveisOuFalha(String username, String email) {
        if (usuarioRepository.findByUsernameIgnoreCase(username).isEmpty() &&
                usernameDisponivelKeycloak(username)) {
            if (!emailDisponivelKeycloak(email)) {
                throw new ValidacaoException("Email indisponivel");
            }
        } else {
            throw new ValidacaoException("Username indisponivel");
        }
    }

    public boolean usernameDisponivelKeycloak(String username) {
        return getListBody(keycloakService.getUserByUsername(username.toLowerCase())).isEmpty();
    }

    public boolean emailDisponivelKeycloak(String email) {
        return getListBody(keycloakService.getUserByEmail(email)).isEmpty();
    }

    private <L> List<L> getListBody(ResponseEntity<List<L>> response) {
        return Optional.ofNullable(response).map(ResponseEntity::getBody).orElse(new ArrayList<>());
    }

    public ClientSecretResponse createClientKeycloak(String nome) {
        var response = keycloakService.createClient(new CreateClientRequest(nome, DESCRIPTION, PROTOCOL, url));
        var clientId = getClientId(response);
        var secretKey = keycloakService.getSecretByClientId(clientId);
        return new ClientSecretResponse(clientId, getSecretKey(secretKey));
    }

    public String getClientId(ResponseEntity<SecretResponse> response) {
        var location = Optional.ofNullable(response).map(ResponseEntity::getHeaders).map(h -> h.get(LOCATION))
                .map(l -> l.toString().replaceAll("]", "")).orElse("");
        return location.substring(location.lastIndexOf(CLIENTS_PATH) + 9);
    }

    public String getUserId(ResponseEntity response) {
        var location = Optional.ofNullable(response).map(ResponseEntity::getHeaders).map(h -> h.get(LOCATION))
                .map(l -> l.toString().replace("]", "")).orElse("");
        return (location.contains("/users/")) ? location.substring(location.lastIndexOf("/users/") + 7) : "";
    }

    public String getSecretKey(ResponseEntity response) {
        return Optional.ofNullable(response).map(r -> (SecretResponse) r.getBody()).map(SecretResponse::getValue)
                .orElse(null);
    }

    public void createGroup(String clientId) {
        keycloakService.createGroup(new GroupsRequest(clientId));
    }
}
