package com.empresa.dto;

import com.empresa.entity.EstadoAsistencia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IngresoMetodoPagoDTO {

    private String tipoPago;
    private Long cantidad;
    private Double total;

}
