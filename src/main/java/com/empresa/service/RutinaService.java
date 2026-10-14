package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.Rutina;

public interface RutinaService {

    List<Rutina> listaTodos();

    Optional<Rutina> buscaPorId(Integer id);

    Rutina inserta(Rutina obj);

    Rutina actualiza(Rutina obj);

    void elimina(Integer id);
}
