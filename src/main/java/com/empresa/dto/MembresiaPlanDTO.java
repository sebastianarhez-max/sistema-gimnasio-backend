package com.empresa.dto;

import com.empresa.entity.EstadoAsistencia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MembresiaPlanDTO {

    private String plan;
    private Long cantidad;

}
