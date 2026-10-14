package com.empresa.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.empresa.dto.AsistenciaRegistroDTO;
import com.empresa.dto.MembresiaRegistroDTO;
import com.empresa.dto.VentaRegistroDTO;
import com.empresa.entity.Asistencia;
import com.empresa.service.RegistroService;
import com.empresa.util.AppSettings;

/** Registros con reglas de negocio: membresía+pago, venta+stock, reserva e ingreso a clase. */
@RestController
@RequestMapping("/url/registro")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class RegistroController {

    @Autowired
    private RegistroService service;

    @PostMapping("/membresia")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> membresia(@RequestBody MembresiaRegistroDTO dto) {
        return service.registrarMembresia(dto);
    }

    @PostMapping("/venta")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> venta(@RequestBody VentaRegistroDTO dto) {
        return service.registrarVenta(dto);
    }

    @PostMapping("/asistencia/reservar")
    @ResponseStatus(HttpStatus.CREATED)
    public Asistencia reservar(@RequestBody AsistenciaRegistroDTO dto) {
        return service.reservar(dto);
    }

    @PostMapping("/asistencia/ingreso")
    public Asistencia ingreso(@RequestBody AsistenciaRegistroDTO dto) {
        return service.registrarIngreso(dto);
    }

    @PutMapping("/asistencia/{id}/falto")
    public Asistencia falto(@PathVariable Integer id) {
        return service.marcarFalto(id);
    }
}
