package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.Asistencia;

public interface AsistenciaService {

    List<Asistencia> listaTodos();

    Optional<Asistencia> buscaPorId(Integer id);

    Asistencia inserta(Asistencia obj);

    Asistencia actualiza(Asistencia obj);

    void elimina(Integer id);
}
