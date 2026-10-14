package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.Producto;

public interface ProductoService {

    List<Producto> listaTodos();

    Optional<Producto> buscaPorId(Integer id);

    Producto inserta(Producto obj);

    Producto actualiza(Producto obj);

    void elimina(Integer id);
}
