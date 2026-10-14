package com.empresa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Entrenador;
import com.empresa.repository.EntrenadorRepository;
import com.empresa.service.EntrenadorService;

@Service
public class EntrenadorServiceImpl implements EntrenadorService {

    @Autowired
    private EntrenadorRepository repository;

    @Override
    public List<Entrenador> listaTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<Entrenador> buscaPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Entrenador inserta(Entrenador obj) {
        obj.setIdEntrenador(null);
        return repository.save(obj);
    }

    @Override
    public Entrenador actualiza(Entrenador obj) {
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
