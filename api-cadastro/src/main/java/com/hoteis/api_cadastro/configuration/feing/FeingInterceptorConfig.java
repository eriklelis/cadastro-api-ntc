package com.hoteis.api_cadastro.configuration.feing;

import com.hoteis.api_cadastro.clients.keycloak.KeycloakAdminAuthenticationService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

@Configuration
@RequiredArgsConstructor
public class FeingInterceptorConfig {
    @Value("${cadatros-api.keycloak.username}")
    private String username;
    @Value("${cadatros-api.keycloak.password}")
    private String password;
    @Value("${cadatros-api.keycloak.grant-type}")
    private String grantType;
    @Value("${cadatros-api.keycloak.client-id}")
    private String clientId;
    @Value("${cadatros-api.keycloak.root-url}")
    private String keycloakUrl;
    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.auth-server-url}")
    private String authServerUrl;


    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String KEYCLOAK_USER = "username=";
    private static final String KEYCLOAK_PWD = "&password=";
    private static final String GRANT_TYPE = "&grant_type=";
    private static final String CLIENT_ID = "&client_id=";
    private static final String AUTH_URL = "%s/realms/%s/protocol/openid-connect/token";

    private final KeycloakAdminAuthenticationService authenticationRequest;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            var destination = DestinationApiURL.valueOf(requestTemplate.feignTarget().url().replace("http://", ""));
            if (DestinationApiURL.KEYCLOAK_ADM_API.equals(destination)) {
                requestInterceptorKeycloakAdm(requestTemplate);
            } else if (DestinationApiURL.KEYCLOAK_AUTH_API.equals(destination)) {
                requestInterceptorKeycloakAuthentication(requestTemplate);
            }
        };
    }



    private void requestInterceptorKeycloakAdm(RequestTemplate requestTemplate) {
        var response = authenticationRequest.auth(getParametersToLogin());
        requestTemplate.header(HEADER_AUTHORIZATION, "Bearer " + response.getAccessToken());
        requestTemplate.target(getURL(keycloakUrl, realm));
    }


    private void requestInterceptorKeycloakAuthentication(RequestTemplate requestTemplate) {
        requestTemplate.target(String.format(AUTH_URL, authServerUrl, realm));
    }

    private String getURL(String url, String realm) {
        return new StringBuilder().append(url).append(realm).toString();
    }

    public byte[] getParametersToLogin() {
        var builder = new StringBuilder();
        builder.append(KEYCLOAK_USER).append(username);
        builder.append(KEYCLOAK_PWD).append(password);
        builder.append(GRANT_TYPE).append(grantType);
        builder.append(CLIENT_ID).append(clientId);

        return builder.toString().getBytes(StandardCharsets.UTF_8);

    }

}
