package com.empresa.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AsistenciaRegistroDTO {

    private Integer idHorario;
    private Integer idCliente;
    private Integer idUsuario;
    private LocalDate fecha;
    private Integer idTipoPago;
}
