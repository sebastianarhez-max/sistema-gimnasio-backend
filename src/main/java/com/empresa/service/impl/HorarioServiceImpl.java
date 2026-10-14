package com.empresa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Horario;
import com.empresa.repository.HorarioRepository;
import com.empresa.service.HorarioService;

@Service
public class HorarioServiceImpl implements HorarioService {

    @Autowired
    private HorarioRepository repository;

    @Override
    public List<Horario> listaTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<Horario> buscaPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Horario inserta(Horario obj) {
        obj.setIdHorario(null);
        return repository.save(obj);
    }

    @Override
    public Horario actualiza(Horario obj) {
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
