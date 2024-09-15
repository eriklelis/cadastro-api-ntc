package com.hoteis.api_cadastro.configuration.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class JWTConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private static final String ROLES = "roles";
    private static final String RESOURCE_ACCESS = "resource_access";
    public static final String CLIENT_ROLES = "roles-conta-digital";

    private static Collection<? extends GrantedAuthority> extractResourceRoles(final Jwt jwt, final String clientId) {
        Map<String, Object> resourceAccess = jwt.getClaim(RESOURCE_ACCESS);
        Map<String, Object> resource;
        Collection<String> resourceRoles;
        if (resourceAccess != null && (resource = (Map<String, Object>) resourceAccess.get(clientId)) != null
            && (resourceRoles = (Collection<String>) resource.get(ROLES)) != null) {
            return resourceRoles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

    @Override
    public AbstractAuthenticationToken convert(final Jwt source) {
        var resourceRoles = extractResourceRoles(source, CLIENT_ROLES);
        var grants = resourceRoles
            .stream()
            .map(role -> new SimpleGrantedAuthority(role.toString())).toList();

        return new JwtAuthenticationToken(source, grants);
    }
}
