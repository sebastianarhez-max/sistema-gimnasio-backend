package com.empresa.dto;

import com.empresa.entity.EstadoAsistencia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductoVendidoDTO {

    private String producto;
    private Long unidades;
    private Double total;

}
