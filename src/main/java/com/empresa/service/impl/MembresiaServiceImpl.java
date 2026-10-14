package com.empresa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Membresia;
import com.empresa.repository.MembresiaRepository;
import com.empresa.service.MembresiaService;

@Service
public class MembresiaServiceImpl implements MembresiaService {

    @Autowired
    private MembresiaRepository repository;

    @Override
    public List<Membresia> listaTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<Membresia> buscaPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Membresia inserta(Membresia obj) {
        obj.setIdMembresia(null);
        return repository.save(obj);
    }

    @Override
    public Membresia actualiza(Membresia obj) {
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
