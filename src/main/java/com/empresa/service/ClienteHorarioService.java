package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.ClienteHorario;

public interface ClienteHorarioService {

    List<ClienteHorario> listaTodos();

    Optional<ClienteHorario> buscaPorId(Integer id);

    ClienteHorario inserta(ClienteHorario obj);

    ClienteHorario actualiza(ClienteHorario obj);

    void elimina(Integer id);
}
