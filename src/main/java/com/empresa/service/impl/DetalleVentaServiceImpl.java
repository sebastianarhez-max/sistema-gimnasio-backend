package com.empresa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.DetalleVenta;
import com.empresa.repository.DetalleVentaRepository;
import com.empresa.service.DetalleVentaService;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {

    @Autowired
    private DetalleVentaRepository repository;

    @Override
    public List<DetalleVenta> listaTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<DetalleVenta> buscaPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public DetalleVenta inserta(DetalleVenta obj) {
        obj.setIdDetalle(null);
        return repository.save(obj);
    }

    @Override
    public DetalleVenta actualiza(DetalleVenta obj) {
        return repository.save(obj);
    }

    @Override
    public void elimina(Integer id) {
        repository.deleteById(id);
    }
}
