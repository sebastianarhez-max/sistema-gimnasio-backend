package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.Torneo;

public interface TorneoService {

    List<Torneo> listaTodos();

    Optional<Torneo> buscaPorId(Integer id);

    Torneo inserta(Torneo obj);

    Torneo actualiza(Torneo obj);

    void elimina(Integer id);
}
