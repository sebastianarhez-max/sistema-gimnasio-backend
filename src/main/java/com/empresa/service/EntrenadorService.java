package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.Entrenador;

public interface EntrenadorService {

    List<Entrenador> listaTodos();

    Optional<Entrenador> buscaPorId(Integer id);

    Entrenador inserta(Entrenador obj);

    Entrenador actualiza(Entrenador obj);

    void elimina(Integer id);
}
