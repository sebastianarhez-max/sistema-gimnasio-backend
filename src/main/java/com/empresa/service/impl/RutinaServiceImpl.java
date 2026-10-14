package com.empresa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Rutina;
import com.empresa.repository.RutinaRepository;
import com.empresa.service.RutinaService;

@Service
public class RutinaServiceImpl implements RutinaService {

    @Autowired
    private RutinaRepository repository;

    @Override
    public List<Rutina> listaTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<Rutina> buscaPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Rutina inserta(Rutina obj) {
        obj.setIdRutina(null);
        return repository.save(obj);
    }

    @Override
    public Rutina actualiza(Rutina obj) {
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
