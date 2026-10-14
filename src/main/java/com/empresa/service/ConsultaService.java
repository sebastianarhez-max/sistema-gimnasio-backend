package com.empresa.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ConsultaService {

    List<Map<String, Object>> asistencia(Integer idHorario, LocalDate fecha);

    List<Map<String, Object>> recaudadoClase(Integer idHorario, LocalDate fecha);

    List<Map<String, Object>> recaudadoPorPago(Integer idHorario, LocalDate fecha);

    List<Map<String, Object>> cupos(Integer idHorario, LocalDate fecha);

    List<Map<String, Object>> membresiasVigentes();

    List<Map<String, Object>> membresiasPorVencer(Integer dias);

    List<Map<String, Object>> historialAsistencia(Integer idCliente);

    List<Map<String, Object>> productosStockBajo();

    List<Map<String, Object>> ventasPorFecha(LocalDate desde, LocalDate hasta);

    List<Map<String, Object>> inscritosTorneo(Integer idTorneo);

    List<Map<String, Object>> horariosEntrenador(Integer idEntrenador);

    Map<String, Object> ingresos(LocalDate desde, LocalDate hasta);
}
