package com.hoteis.api_cadastro.controller;

import com.hoteis.api_cadastro.dto.CriacaoUsuarioRequestDTO;
import com.hoteis.api_cadastro.dto.CriacaoUsuarioResponseDTO;
import com.hoteis.api_cadastro.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<CriacaoUsuarioResponseDTO> criarUsuarioKeycloak(@RequestBody CriacaoUsuarioRequestDTO criacaoUsuarioRequestDTO) {
        var response = usuarioService.criarUsuario(criacaoUsuarioRequestDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequestUri()
                                                                .build()
                                                                .toUri()).body(response);
    }
}
