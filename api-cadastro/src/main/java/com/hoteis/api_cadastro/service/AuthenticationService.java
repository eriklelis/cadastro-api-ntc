package com.hoteis.api_cadastro.service;

import com.hoteis.api_cadastro.dto.auth.AuthenticationResponseDTO;
import com.hoteis.api_cadastro.dto.auth.UserAuthenticationDTO;

public interface AuthenticationService {
    AuthenticationResponseDTO authenticateReturnToken(UserAuthenticationDTO userAuthenticationDTO);
}
