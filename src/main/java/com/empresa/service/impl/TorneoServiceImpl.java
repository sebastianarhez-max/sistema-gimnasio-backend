package com.empresa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Torneo;
import com.empresa.repository.TorneoRepository;
import com.empresa.service.TorneoService;

@Service
public class TorneoServiceImpl implements TorneoService {

    @Autowired
    private TorneoRepository repository;

    @Override
    public List<Torneo> listaTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<Torneo> buscaPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Torneo inserta(Torneo obj) {
        obj.setIdTorneo(null);
        return repository.save(obj);
    }

    @Override
    public Torneo actualiza(Torneo obj) {
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
