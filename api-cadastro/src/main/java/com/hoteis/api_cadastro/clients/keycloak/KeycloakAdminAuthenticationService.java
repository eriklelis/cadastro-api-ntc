package com.hoteis.api_cadastro.clients.keycloak;

import com.hoteis.api_cadastro.clients.keycloak.response.AuthenticationResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "keycloakAdminAuthentication", url = "${cadatros-api.keycloak.oauth-url}")
public interface KeycloakAdminAuthenticationService {
    @PostMapping(consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    AuthenticationResponse auth(@RequestBody Object param);

}
