package com.empresa.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VentaRegistroDTO {

    private Integer idUsuario;
    private Integer idCliente;
    private List<ItemVentaDTO> items;
}
