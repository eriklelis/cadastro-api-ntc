package com.hoteis.api_cadastro.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private String guid;
    private String nome;
    private String telefone;
    private String email;
}
