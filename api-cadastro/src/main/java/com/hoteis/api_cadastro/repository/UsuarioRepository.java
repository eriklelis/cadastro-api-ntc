package com.hoteis.api_cadastro.repository;

import com.hoteis.api_cadastro.domain.Usuario;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends BaseApiRepository<Usuario, Long> {

    Optional<Usuario> findByUsernameIgnoreCase(String username);
}
