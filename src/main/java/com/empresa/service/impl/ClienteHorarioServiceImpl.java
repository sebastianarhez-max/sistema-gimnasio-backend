package com.empresa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.ClienteHorario;
import com.empresa.repository.ClienteHorarioRepository;
import com.empresa.service.ClienteHorarioService;

@Service
public class ClienteHorarioServiceImpl implements ClienteHorarioService {

    @Autowired
    private ClienteHorarioRepository repository;

    @Override
    public List<ClienteHorario> listaTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<ClienteHorario> buscaPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public ClienteHorario inserta(ClienteHorario obj) {
        obj.setIdClienteHorario(null);
        return repository.save(obj);
    }

    @Override
    public ClienteHorario actualiza(ClienteHorario obj) {
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
