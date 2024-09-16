package com.hoteis.api_cadastro.service;

import com.hoteis.api_cadastro.dto.cliente.CriacaoClienteRequestDTO;
import com.hoteis.api_cadastro.dto.cliente.CriacaoClienteResponseDTO;

public interface ClienteService {
    CriacaoClienteResponseDTO criarCliente(CriacaoClienteRequestDTO criacaoUsuarioRequestDTO);
}
