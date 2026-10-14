package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.Membresia;

public interface MembresiaService {

    List<Membresia> listaTodos();

    Optional<Membresia> buscaPorId(Integer id);

    Membresia inserta(Membresia obj);

    Membresia actualiza(Membresia obj);

    void elimina(Integer id);
}
