package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.InscripcionTorneo;

public interface InscripcionTorneoService {

    List<InscripcionTorneo> listaTodos();

    Optional<InscripcionTorneo> buscaPorId(Integer id);

    InscripcionTorneo inserta(InscripcionTorneo obj);

    InscripcionTorneo actualiza(InscripcionTorneo obj);

    void elimina(Integer id);
}
