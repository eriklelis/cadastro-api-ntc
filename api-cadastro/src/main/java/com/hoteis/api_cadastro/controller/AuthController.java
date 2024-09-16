package com.hoteis.api_cadastro.controller;


import com.hoteis.api_cadastro.dto.auth.AuthenticationResponseDTO;
import com.hoteis.api_cadastro.dto.auth.UserAuthenticationDTO;
import com.hoteis.api_cadastro.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthenticationResponseDTO> auth(
            @RequestBody UserAuthenticationDTO userAuthenticationDTO) {
        return ResponseEntity.ok(authenticationService.authenticateReturnToken(userAuthenticationDTO));
    }
}
