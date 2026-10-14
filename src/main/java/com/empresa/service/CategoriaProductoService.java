package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.CategoriaProducto;

public interface CategoriaProductoService {

    List<CategoriaProducto> listaTodos();

    Optional<CategoriaProducto> buscaPorId(Integer id);

    CategoriaProducto inserta(CategoriaProducto obj);

    CategoriaProducto actualiza(CategoriaProducto obj);

    void elimina(Integer id);
}
