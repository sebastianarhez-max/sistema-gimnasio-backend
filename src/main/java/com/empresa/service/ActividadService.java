package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.Actividad;

public interface ActividadService {

    List<Actividad> listaTodos();

    Optional<Actividad> buscaPorId(Integer id);

    Actividad inserta(Actividad obj);

    Actividad actualiza(Actividad obj);

    void elimina(Integer id);
}
