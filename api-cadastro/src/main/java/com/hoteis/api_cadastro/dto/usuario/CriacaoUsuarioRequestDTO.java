package com.hoteis.api_cadastro.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CriacaoUsuarioRequestDTO extends UsuarioDTO {
    private String senha;
    private String email;
}
