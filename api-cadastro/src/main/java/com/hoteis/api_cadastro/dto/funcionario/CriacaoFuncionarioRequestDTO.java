package com.hoteis.api_cadastro.dto.funcionario;

import com.hoteis.api_cadastro.dto.usuario.CriacaoUsuarioRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriacaoFuncionarioRequestDTO extends FuncionarioDTO {
    private CriacaoUsuarioRequestDTO usuarioRequest;

}
