package com.hoteis.api_cadastro.service;

import com.hoteis.api_cadastro.dto.CriacaoUsuarioRequestDTO;
import com.hoteis.api_cadastro.dto.CriacaoUsuarioResponseDTO;

public interface UsuarioService {
    CriacaoUsuarioResponseDTO criarUsuario(CriacaoUsuarioRequestDTO criacaoUsuarioRequestDTO);
}
