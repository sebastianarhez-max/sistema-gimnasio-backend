package com.empresa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Cliente;
import com.empresa.repository.ClienteRepository;
import com.empresa.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Override
    public List<Cliente> listaTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<Cliente> buscaPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Cliente inserta(Cliente obj) {
        obj.setIdCliente(null);
        return repository.save(obj);
    }

    @Override
    public Cliente actualiza(Cliente obj) {
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
