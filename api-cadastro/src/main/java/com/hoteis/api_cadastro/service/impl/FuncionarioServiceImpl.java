package com.hoteis.api_cadastro.service.impl;

import com.hoteis.api_cadastro.domain.Funcionario;
import com.hoteis.api_cadastro.dto.funcionario.CriacaoFuncionarioRequestDTO;
import com.hoteis.api_cadastro.dto.funcionario.CriacaoFuncionarioResponseDTO;
import com.hoteis.api_cadastro.repository.FuncionarioRepository;
import com.hoteis.api_cadastro.service.FuncionarioService;
import com.hoteis.api_cadastro.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FuncionarioServiceImpl implements FuncionarioService {
    @Value("${cadatros-api.keycloak.role-name-adm}")
    private String roleNameADM;
    @Value("${cadatros-api.keycloak.collection-role-adm}")
    private String collectionRoleADM;


    private final UsuarioService usuarioService;
    private final FuncionarioRepository funcionarioRepository;

    @Override
    public CriacaoFuncionarioResponseDTO criarFuncionario(CriacaoFuncionarioRequestDTO criacaoFuncionarioRequestDTO) {
        var criacaoUsuario = usuarioService.criarUsuario(criacaoFuncionarioRequestDTO.getUsuarioRequest(), collectionRoleADM, roleNameADM);
        var funcionarioDB = funcionarioRepository.save(Funcionario.builder()
                                                                    .nome(criacaoFuncionarioRequestDTO.getNome())
                                                                    .usuario(criacaoUsuario.usuario())
                                                                    .build());
        return new CriacaoFuncionarioResponseDTO(funcionarioDB, criacaoUsuario.criacaoUsuarioResponseDTO());
    }
}
