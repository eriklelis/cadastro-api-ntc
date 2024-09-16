package com.hoteis.api_cadastro.clients.keycloak;

import com.hoteis.api_cadastro.clients.keycloak.response.AuthenticationKeycloakResponseDTO;
import com.hoteis.api_cadastro.configuration.feing.FeingInterceptorConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    url = "KEYCLOAK_AUTH_API",
    value = "keycloakClientAuthentication",
    configuration = FeingInterceptorConfig.class
)
public interface KeycloakClientAuthenticationService {

    @PostMapping(consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    AuthenticationKeycloakResponseDTO auth(@RequestBody Object param);

}
