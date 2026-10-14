package com.empresa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Pago;
import com.empresa.repository.PagoRepository;
import com.empresa.service.PagoService;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired
    private PagoRepository repository;

    @Override
    public List<Pago> listaTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<Pago> buscaPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Pago inserta(Pago obj) {
        obj.setIdPago(null);
        return repository.save(obj);
    }

    @Override
    public Pago actualiza(Pago obj) {
        return repository.save(obj);
    }

    @Override
    public void elimina(Integer id) {
        repository.deleteById(id);
    }
}
