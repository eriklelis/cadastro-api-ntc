package com.hoteis.api_cadastro.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "usuario")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario extends Base {
    @Column(name = "username")
    private String username;

    @Column(name = "client_id")
    private String clientId;
    @OneToOne(mappedBy = "usuario", orphanRemoval = true)
    private Funcionario funcionario;

}
