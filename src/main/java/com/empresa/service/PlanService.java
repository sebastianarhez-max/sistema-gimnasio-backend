package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.Plan;

public interface PlanService {

    List<Plan> listaTodos();

    Optional<Plan> buscaPorId(Integer id);

    Plan inserta(Plan obj);

    Plan actualiza(Plan obj);

    void elimina(Integer id);
}
