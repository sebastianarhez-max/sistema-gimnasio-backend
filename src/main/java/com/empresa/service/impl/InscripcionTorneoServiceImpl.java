package com.empresa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.InscripcionTorneo;
import com.empresa.repository.InscripcionTorneoRepository;
import com.empresa.service.InscripcionTorneoService;

@Service
public class InscripcionTorneoServiceImpl implements InscripcionTorneoService {

    @Autowired
    private InscripcionTorneoRepository repository;

    @Override
    public List<InscripcionTorneo> listaTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<InscripcionTorneo> buscaPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public InscripcionTorneo inserta(InscripcionTorneo obj) {
        obj.setIdInscripcion(null);
        return repository.save(obj);
    }

    @Override
    public InscripcionTorneo actualiza(InscripcionTorneo obj) {
        return repository.save(obj);
    }

    @Override
    public void elimina(Integer id) {
        repository.findById(id).ifPresent(e -> {
            e.setEstado(false);
            repository.save(e);
        });
    }
}
