package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.DetalleVenta;

public interface DetalleVentaService {

    List<DetalleVenta> listaTodos();

    Optional<DetalleVenta> buscaPorId(Integer id);

    DetalleVenta inserta(DetalleVenta obj);

    DetalleVenta actualiza(DetalleVenta obj);

    void elimina(Integer id);
}
