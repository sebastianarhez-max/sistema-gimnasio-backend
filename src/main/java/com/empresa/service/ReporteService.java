package com.empresa.service;

import java.time.LocalDate;
import java.util.List;

import com.empresa.dto.*;

public interface ReporteService {

    List<MembresiaPlanDTO> membresiasPorPlan();

    List<AsistenciaEstadoDTO> asistenciaPorEstado(Integer idHorario, LocalDate fecha);

    List<IngresoMetodoPagoDTO> ingresosPorMetodoPago(LocalDate desde, LocalDate hasta);

    List<ClaseConcurrenciaDTO> clasesMasConcurridas();

    List<ProductoVendidoDTO> productosMasVendidos();
}
