package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.EvaluacionFisica;

public interface EvaluacionFisicaService {

    List<EvaluacionFisica> listaTodos();

    Optional<EvaluacionFisica> buscaPorId(Integer id);

    EvaluacionFisica inserta(EvaluacionFisica obj);

    EvaluacionFisica actualiza(EvaluacionFisica obj);

    void elimina(Integer id);
}
