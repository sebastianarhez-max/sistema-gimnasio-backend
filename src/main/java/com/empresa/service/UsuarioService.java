package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.Usuario;

public interface UsuarioService {

    List<Usuario> listaTodos();

    Optional<Usuario> buscaPorId(Integer id);

    Usuario inserta(Usuario obj);

    Usuario actualiza(Usuario obj);

    void elimina(Integer id);
}
