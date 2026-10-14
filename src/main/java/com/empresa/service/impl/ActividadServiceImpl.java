package com.empresa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Actividad;
import com.empresa.repository.ActividadRepository;
import com.empresa.service.ActividadService;

@Service
public class ActividadServiceImpl implements ActividadService {

    @Autowired
    private ActividadRepository repository;

    @Override
    public List<Actividad> listaTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<Actividad> buscaPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Actividad inserta(Actividad obj) {
        obj.setIdActividad(null);
        return repository.save(obj);
    }

    @Override
    public Actividad actualiza(Actividad obj) {
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
