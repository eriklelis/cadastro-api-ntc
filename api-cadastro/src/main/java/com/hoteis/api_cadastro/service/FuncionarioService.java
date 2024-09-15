package com.hoteis.api_cadastro.service;

import com.hoteis.api_cadastro.dto.funcionario.CriacaoFuncionarioRequestDTO;
import com.hoteis.api_cadastro.dto.funcionario.CriacaoFuncionarioResponseDTO;
import com.hoteis.api_cadastro.dto.usuario.CriacaoUsuarioRequestDTO;
import com.hoteis.api_cadastro.dto.usuario.CriacaoUsuarioResponseDTO;

public interface FuncionarioService {
    CriacaoFuncionarioResponseDTO criarFuncionario(CriacaoFuncionarioRequestDTO criacaoUsuarioRequestDTO);
}
