package com.hoteis.api_cadastro.service.impl;

import com.hoteis.api_cadastro.domain.Cliente;
import com.hoteis.api_cadastro.dto.cliente.CriacaoClienteRequestDTO;
import com.hoteis.api_cadastro.dto.cliente.CriacaoClienteResponseDTO;
import com.hoteis.api_cadastro.repository.ClienteRepository;
import com.hoteis.api_cadastro.service.ClienteService;
import com.hoteis.api_cadastro.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    @Value("${cadatros-api.keycloak.role-name-cli}")
    private String roleNameCLI;
    @Value("${cadatros-api.keycloak.collection-role-cli}")
    private String collectionRoleCLI;
    private final UsuarioService usuarioService;
    private final ClienteRepository clienteRepository;

    public CriacaoClienteResponseDTO criarCliente(CriacaoClienteRequestDTO criacaoClienteRequestDTO) {
        var criacaoUsuario = usuarioService.criarUsuario(criacaoClienteRequestDTO.getUsuarioRequest(), collectionRoleCLI, roleNameCLI);
        var clienteDb = clienteRepository.save(Cliente.builder()
                                                            .nome(criacaoClienteRequestDTO.getNome())
                                                            .email(criacaoClienteRequestDTO.getEmail())
                                                            .telefone(criacaoClienteRequestDTO.getTelefone())
                                                            .usuario(criacaoUsuario.usuario())
                                                            .build());
        return new CriacaoClienteResponseDTO(clienteDb, criacaoUsuario.criacaoUsuarioResponseDTO());
    }
}
