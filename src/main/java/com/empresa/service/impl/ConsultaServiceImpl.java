package com.empresa.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.repository.ConsultaRepository;
import com.empresa.service.ConsultaService;

@Service
public class ConsultaServiceImpl implements ConsultaService {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public List<Map<String, Object>> asistencia(Integer idHorario, LocalDate fecha) {
        return repository.asistencia(idHorario, fecha);
    }

    @Override
    public List<Map<String, Object>> recaudadoClase(Integer idHorario, LocalDate fecha) {
        return repository.recaudadoClase(idHorario, fecha);
    }

    @Override
    public List<Map<String, Object>> recaudadoPorPago(Integer idHorario, LocalDate fecha) {
        return repository.recaudadoPorPago(idHorario, fecha);
    }

    @Override
    public List<Map<String, Object>> cupos(Integer idHorario, LocalDate fecha) {
        return repository.cupos(idHorario, fecha);
    }

    @Override
    public List<Map<String, Object>> membresiasVigentes() {
        return repository.membresiasVigentes();
    }

    @Override
    public List<Map<String, Object>> membresiasPorVencer(Integer dias) {
        return repository.membresiasPorVencer(dias);
    }

    @Override
    public List<Map<String, Object>> historialAsistencia(Integer idCliente) {
        return repository.historialAsistencia(idCliente);
    }

    @Override
    public List<Map<String, Object>> productosStockBajo() {
        return repository.productosStockBajo();
    }

    @Override
    public List<Map<String, Object>> ventasPorFecha(LocalDate desde, LocalDate hasta) {
        return repository.ventasPorFecha(desde, hasta);
    }

    @Override
    public List<Map<String, Object>> inscritosTorneo(Integer idTorneo) {
        return repository.inscritosTorneo(idTorneo);
    }

    @Override
    public List<Map<String, Object>> horariosEntrenador(Integer idEntrenador) {
        return repository.horariosEntrenador(idEntrenador);
    }

    @Override
    public Map<String, Object> ingresos(LocalDate desde, LocalDate hasta) {
        return repository.ingresos(desde, hasta);
    }
}
