package com.empresa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.empresa.entity.TipoPago;
import com.empresa.service.TipoPagoService;
import com.empresa.util.AppSettings;

@RestController
@RequestMapping("/url/tipopago")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class TipoPagoController {

    @Autowired
    private TipoPagoService service;

    @GetMapping
    public ResponseEntity<List<TipoPago>> listaTodos() {
        return ResponseEntity.ok(service.listaTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscaPorId(@PathVariable Integer id) {
        Optional<TipoPago> opt = service.buscaPorId(id);
        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        }
        HashMap<String, Object> salida = new HashMap<>();
        salida.put("error", "Registro no encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(salida);
    }

    @PostMapping
    public ResponseEntity<?> registro(@RequestBody TipoPago obj) {
        HashMap<String, Object> salida = new HashMap<>();
        TipoPago objSalida = service.inserta(obj);
        salida.put("data", objSalida);
        salida.put("mensaje", "Registro exitoso");
        return ResponseEntity.ok(salida);
    }

    @PutMapping
    public ResponseEntity<?> actualiza(@RequestBody TipoPago obj) {
        HashMap<String, Object> salida = new HashMap<>();
        if (obj.getIdTipoPago() == null || service.buscaPorId(obj.getIdTipoPago()).isEmpty()) {
            salida.put("error", "El registro no existe: envía el campo idTipoPago en el JSON");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(salida);
        }
        TipoPago objSalida = service.actualiza(obj);
        salida.put("data", objSalida);
        salida.put("mensaje", "Actualización exitosa");
        return ResponseEntity.ok(salida);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> elimina(@PathVariable Integer id) {
        HashMap<String, Object> salida = new HashMap<>();
        if (service.buscaPorId(id).isEmpty()) {
            salida.put("error", "Registro no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(salida);
        }
        service.elimina(id);
        salida.put("mensaje", "Eliminación exitosa");
        return ResponseEntity.ok(salida);
    }
}
