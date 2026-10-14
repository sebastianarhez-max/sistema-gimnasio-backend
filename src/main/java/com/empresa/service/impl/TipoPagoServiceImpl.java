package com.empresa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.TipoPago;
import com.empresa.repository.TipoPagoRepository;
import com.empresa.service.TipoPagoService;

@Service
public class TipoPagoServiceImpl implements TipoPagoService {

    @Autowired
    private TipoPagoRepository repository;

    @Override
    public List<TipoPago> listaTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<TipoPago> buscaPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public TipoPago inserta(TipoPago obj) {
        obj.setIdTipoPago(null);
        return repository.save(obj);
    }

    @Override
    public TipoPago actualiza(TipoPago obj) {
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
