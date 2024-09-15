package com.hoteis.api_cadastro.service;

import com.hoteis.api_cadastro.dto.usuario.CriacaoUsuarioRequestDTO;
import com.hoteis.api_cadastro.dto.usuario.CriacaoUsuarioResponseDTO;
import com.hoteis.api_cadastro.service.impl.UsuarioServiceImpl;

public interface UsuarioService {
    UsuarioServiceImpl.CriacaoUsuarioDTO criarUsuario(CriacaoUsuarioRequestDTO criacaoUsuarioRequestDTO);
}
