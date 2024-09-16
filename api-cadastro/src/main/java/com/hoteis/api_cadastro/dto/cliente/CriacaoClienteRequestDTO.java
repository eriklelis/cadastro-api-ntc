package com.hoteis.api_cadastro.dto.cliente;

import com.hoteis.api_cadastro.dto.usuario.CriacaoUsuarioRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriacaoClienteRequestDTO extends ClienteDTO {
    private CriacaoUsuarioRequestDTO usuarioRequest;

}
