package com.empresa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Plan;
import com.empresa.repository.PlanRepository;
import com.empresa.service.PlanService;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository repository;

    @Override
    public List<Plan> listaTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<Plan> buscaPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Plan inserta(Plan obj) {
        obj.setIdPlan(null);
        return repository.save(obj);
    }

    @Override
    public Plan actualiza(Plan obj) {
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
