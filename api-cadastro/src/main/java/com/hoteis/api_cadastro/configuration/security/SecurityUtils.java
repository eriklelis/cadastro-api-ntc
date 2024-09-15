package com.hoteis.api_cadastro.configuration.security;

import com.hoteis.api_cadastro.domain.Usuario;
import com.hoteis.api_cadastro.exception.NaoAutorizadoException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public final class SecurityUtils {

    private SecurityUtils() {
    }

    public static Optional<Usuario> getCurrentUser() {
        return obterAuthentication()
            .filter(auth -> auth.getPrincipal() instanceof Usuario)
            .map(auth -> (Usuario) auth.getPrincipal());
    }

    public static Usuario getCurrentUserOrFail() {
        return getCurrentUser().orElseThrow(NaoAutorizadoException::new);
    }

    public static Optional<String> getCurrentUserJWT() {
        return obterAuthentication()
            .filter(auth -> auth.getCredentials() instanceof String)
            .map(auth -> (String) auth.getCredentials());
    }

    public static boolean isAuthenticated() {
        return obterAuthentication()
            .map(auth -> !auth.getAuthorities().isEmpty())
            .orElse(false);
    }

    public static boolean isNotAuthenticated() {
        return !isAuthenticated();
    }

    public static boolean isCurrentUserInRole(String authority) {
        return obterAuthentication()
            .map(authentication -> authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(authority)))
            .orElse(false);
    }

    public static boolean isCurrentUserNotInRole(String authority) {
        return !isCurrentUserInRole(authority);
    }

    private static Optional<Authentication> obterAuthentication() {
        final var securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication());
    }
}
