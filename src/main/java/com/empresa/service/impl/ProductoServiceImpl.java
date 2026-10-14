package com.empresa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Producto;
import com.empresa.repository.ProductoRepository;
import com.empresa.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository repository;

    @Override
    public List<Producto> listaTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<Producto> buscaPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Producto inserta(Producto obj) {
        obj.setIdProducto(null);
        return repository.save(obj);
    }

    @Override
    public Producto actualiza(Producto obj) {
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
