package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.Pago;

public interface PagoService {

    List<Pago> listaTodos();

    Optional<Pago> buscaPorId(Integer id);

    Pago inserta(Pago obj);

    Pago actualiza(Pago obj);

    void elimina(Integer id);
}
