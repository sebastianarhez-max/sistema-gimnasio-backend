package com.empresa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.EvaluacionFisica;
import com.empresa.repository.EvaluacionFisicaRepository;
import com.empresa.service.EvaluacionFisicaService;

@Service
public class EvaluacionFisicaServiceImpl implements EvaluacionFisicaService {

    @Autowired
    private EvaluacionFisicaRepository repository;

    @Override
    public List<EvaluacionFisica> listaTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<EvaluacionFisica> buscaPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public EvaluacionFisica inserta(EvaluacionFisica obj) {
        obj.setIdEvaluacion(null);
        return repository.save(obj);
    }

    @Override
    public EvaluacionFisica actualiza(EvaluacionFisica obj) {
        return repository.save(obj);
    }

    @Override
    public void elimina(Integer id) {
        repository.deleteById(id);
    }
}
