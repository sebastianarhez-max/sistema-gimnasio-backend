package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.Venta;

public interface VentaService {

    List<Venta> listaTodos();

    Optional<Venta> buscaPorId(Integer id);

    Venta inserta(Venta obj);

    Venta actualiza(Venta obj);

    void elimina(Integer id);
}
