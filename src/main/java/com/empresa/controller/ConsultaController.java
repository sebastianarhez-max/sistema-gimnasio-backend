package com.empresa.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.empresa.service.ConsultaService;
import com.empresa.util.AppSettings;

@RestController
@RequestMapping("/url/consulta")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ConsultaController {

    @Autowired
    private ConsultaService service;

    /** C1. Quién va a llegar / quién ya llegó a una clase en una fecha. */
    @GetMapping("/asistencia")
    public ResponseEntity<?> asistencia(@RequestParam Integer idHorario, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(service.asistencia(idHorario, fecha));
    }

    /** C2. Recaudado hasta el momento en una clase y cuántas personas ingresaron. */
    @GetMapping("/recaudado-clase")
    public ResponseEntity<?> recaudadoClase(@RequestParam Integer idHorario, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(service.recaudadoClase(idHorario, fecha));
    }

    /** C3. Recaudado de una clase desglosado por método de pago. */
    @GetMapping("/recaudado-clase/por-pago")
    public ResponseEntity<?> recaudadoPorPago(@RequestParam Integer idHorario, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(service.recaudadoPorPago(idHorario, fecha));
    }

    /** C4. Cupos disponibles de una clase en una fecha. */
    @GetMapping("/cupos")
    public ResponseEntity<?> cupos(@RequestParam Integer idHorario, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(service.cupos(idHorario, fecha));
    }

    /** C5. Membresías vigentes hoy. */
    @GetMapping("/membresias-vigentes")
    public ResponseEntity<?> membresiasVigentes() {
        return ResponseEntity.ok(service.membresiasVigentes());
    }

    /** C6. Membresías que vencen en los próximos N días. */
    @GetMapping("/membresias-por-vencer")
    public ResponseEntity<?> membresiasPorVencer(@RequestParam(defaultValue = "7") Integer dias) {
        return ResponseEntity.ok(service.membresiasPorVencer(dias));
    }

    /** C7. Historial de asistencia de un cliente. */
    @GetMapping("/cliente/{idCliente}/historial-asistencia")
    public ResponseEntity<?> historialAsistencia(@PathVariable Integer idCliente) {
        return ResponseEntity.ok(service.historialAsistencia(idCliente));
    }

    /** C8. Productos con stock bajo (stock <= stock mínimo). */
    @GetMapping("/productos-stock-bajo")
    public ResponseEntity<?> productosStockBajo() {
        return ResponseEntity.ok(service.productosStockBajo());
    }

    /** C9. Ventas de la tienda en un rango de fechas. */
    @GetMapping("/ventas")
    public ResponseEntity<?> ventasPorFecha(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        return ResponseEntity.ok(service.ventasPorFecha(desde, hasta));
    }

    /** C11. Inscritos de un torneo con su entrenador y resultado. */
    @GetMapping("/torneo/{idTorneo}/inscritos")
    public ResponseEntity<?> inscritosTorneo(@PathVariable Integer idTorneo) {
        return ResponseEntity.ok(service.inscritosTorneo(idTorneo));
    }

    /** C12. Horarios de un entrenador. */
    @GetMapping("/entrenador/{idEntrenador}/horarios")
    public ResponseEntity<?> horariosEntrenador(@PathVariable Integer idEntrenador) {
        return ResponseEntity.ok(service.horariosEntrenador(idEntrenador));
    }

    /** C10. Ingresos totales: membresías + tienda + clases libres. */
    @GetMapping("/ingresos")
    public ResponseEntity<?> ingresos(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        return ResponseEntity.ok(service.ingresos(desde, hasta));
    }
}
