package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.Horario;

public interface HorarioService {

    List<Horario> listaTodos();

    Optional<Horario> buscaPorId(Integer id);

    Horario inserta(Horario obj);

    Horario actualiza(Horario obj);

    void elimina(Integer id);
}
