package com.empresa.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MembresiaRegistroDTO {

    private Integer idCliente;
    private Integer idPlan;
    private LocalDate fechaInicio;
    private Integer idTipoPago;
}
