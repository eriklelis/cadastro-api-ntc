package com.hoteis.api_cadastro.dto.funcionario;

import com.hoteis.api_cadastro.domain.Funcionario;
import com.hoteis.api_cadastro.domain.Usuario;
import com.hoteis.api_cadastro.dto.usuario.CriacaoUsuarioResponseDTO;
import com.hoteis.api_cadastro.dto.usuario.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CriacaoFuncionarioResponseDTO extends FuncionarioDTO {

    private String username;
    private String clientId;
    private String clientSecret;

    public CriacaoFuncionarioResponseDTO(Funcionario funcionario, CriacaoUsuarioResponseDTO criacaoUsuarioResponseDTO) {
        super(funcionario.getGuid(), funcionario.getNome());
        this.username = criacaoUsuarioResponseDTO.getUsername();
        this.clientId = criacaoUsuarioResponseDTO.getClientId();
        this.clientSecret = criacaoUsuarioResponseDTO.getClientSecret();
    }

}
