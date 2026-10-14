package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.TipoPago;

public interface TipoPagoService {

    List<TipoPago> listaTodos();

    Optional<TipoPago> buscaPorId(Integer id);

    TipoPago inserta(TipoPago obj);

    TipoPago actualiza(TipoPago obj);

    void elimina(Integer id);
}
