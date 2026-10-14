package com.empresa.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.empresa.dto.*;
import com.empresa.service.ReporteService;
import com.empresa.util.AppSettings;

/** Reportes para gráficos (DTO con cantidad/total), igual que los reportes de proveedor del profesor. */
@RestController
@RequestMapping("/url/reporte")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ReporteController {

    @Autowired
    private ReporteService service;

    @GetMapping("/membresias-por-plan")
    public ResponseEntity<List<MembresiaPlanDTO>> membresiasPorPlan() {
        return ResponseEntity.ok(service.membresiasPorPlan());
    }

    @GetMapping("/asistencia-por-estado")
    public ResponseEntity<List<AsistenciaEstadoDTO>> asistenciaPorEstado(@RequestParam Integer idHorario,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(service.asistenciaPorEstado(idHorario, fecha));
    }

    @GetMapping("/ingresos-por-metodo-pago")
    public ResponseEntity<List<IngresoMetodoPagoDTO>> ingresosPorMetodoPago(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        return ResponseEntity.ok(service.ingresosPorMetodoPago(desde, hasta));
    }

    @GetMapping("/clases-mas-concurridas")
    public ResponseEntity<List<ClaseConcurrenciaDTO>> clasesMasConcurridas() {
        return ResponseEntity.ok(service.clasesMasConcurridas());
    }

    @GetMapping("/productos-mas-vendidos")
    public ResponseEntity<List<ProductoVendidoDTO>> productosMasVendidos() {
        return ResponseEntity.ok(service.productosMasVendidos());
    }
}
