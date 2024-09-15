package com.hoteis.api_cadastro.dto.usuario;

import com.hoteis.api_cadastro.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CriacaoUsuarioResponseDTO extends UsuarioDTO {

    private String clientId;
    private String clientSecret;

    public CriacaoUsuarioResponseDTO(Usuario usuario) {
        this.clientId = usuario.getClientId();
        super.setGuid(usuario.getGuid());
        super.setUsername(usuario.getUsername());
    }
}
