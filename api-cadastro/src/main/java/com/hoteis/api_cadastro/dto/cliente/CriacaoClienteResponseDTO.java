package com.hoteis.api_cadastro.dto.cliente;

import com.hoteis.api_cadastro.domain.Cliente;
import com.hoteis.api_cadastro.domain.Funcionario;
import com.hoteis.api_cadastro.dto.usuario.CriacaoUsuarioResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CriacaoClienteResponseDTO extends ClienteDTO {

    private String username;
    private String clientId;
    private String clientSecret;

    public CriacaoClienteResponseDTO(Cliente cliente, CriacaoUsuarioResponseDTO criacaoUsuarioResponseDTO) {
        super(cliente.getGuid(), cliente.getNome(), cliente.getTelefone(),cliente.getEmail());
        this.username = criacaoUsuarioResponseDTO.getUsername();
        this.clientId = criacaoUsuarioResponseDTO.getClientId();
        this.clientSecret = criacaoUsuarioResponseDTO.getClientSecret();
    }

}
