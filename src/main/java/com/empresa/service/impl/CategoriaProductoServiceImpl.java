package com.empresa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.CategoriaProducto;
import com.empresa.repository.CategoriaProductoRepository;
import com.empresa.service.CategoriaProductoService;

@Service
public class CategoriaProductoServiceImpl implements CategoriaProductoService {

    @Autowired
    private CategoriaProductoRepository repository;

    @Override
    public List<CategoriaProducto> listaTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<CategoriaProducto> buscaPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public CategoriaProducto inserta(CategoriaProducto obj) {
        obj.setIdCategoria(null);
        return repository.save(obj);
    }

    @Override
    public CategoriaProducto actualiza(CategoriaProducto obj) {
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
