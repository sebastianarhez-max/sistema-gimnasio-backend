package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.Cliente;

public interface ClienteService {

    List<Cliente> listaTodos();

    Optional<Cliente> buscaPorId(Integer id);

    Cliente inserta(Cliente obj);

    Cliente actualiza(Cliente obj);

    void elimina(Integer id);
}
