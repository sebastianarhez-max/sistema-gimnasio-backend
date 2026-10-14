package com.empresa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Asistencia;
import com.empresa.repository.AsistenciaRepository;
import com.empresa.service.AsistenciaService;

@Service
public class AsistenciaServiceImpl implements AsistenciaService {

    @Autowired
    private AsistenciaRepository repository;

    @Override
    public List<Asistencia> listaTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<Asistencia> buscaPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Asistencia inserta(Asistencia obj) {
        obj.setIdAsistencia(null);
        return repository.save(obj);
    }

    @Override
    public Asistencia actualiza(Asistencia obj) {
        return repository.save(obj);
    }

    @Override
    public void elimina(Integer id) {
        repository.deleteById(id);
    }
}
