package com.empresa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Venta;
import com.empresa.repository.VentaRepository;
import com.empresa.service.VentaService;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository repository;

    @Override
    public List<Venta> listaTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<Venta> buscaPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Venta inserta(Venta obj) {
        obj.setIdVenta(null);
        return repository.save(obj);
    }

    @Override
    public Venta actualiza(Venta obj) {
        return repository.save(obj);
    }

    @Override
    public void elimina(Integer id) {
        repository.deleteById(id);
    }
}
