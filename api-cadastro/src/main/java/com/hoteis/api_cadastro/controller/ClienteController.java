package com.hoteis.api_cadastro.controller;

import com.hoteis.api_cadastro.dto.funcionario.CriacaoFuncionarioRequestDTO;
import com.hoteis.api_cadastro.dto.funcionario.CriacaoFuncionarioResponseDTO;
import com.hoteis.api_cadastro.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("cliente")
@RequiredArgsConstructor
public class ClienteController {
    private final FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<CriacaoFuncionarioResponseDTO> criarClienteKeycloak(@RequestBody CriacaoFuncionarioRequestDTO criacaoFuncionarioRequestDTO) {
        var response = funcionarioService.criarFuncionario(criacaoFuncionarioRequestDTO);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{guid}")
                .buildAndExpand(response.getGuid())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }
}
