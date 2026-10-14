package com.empresa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.empresa.entity.Venta;
import com.empresa.service.VentaService;
import com.empresa.util.AppSettings;

@RestController
@RequestMapping("/url/venta")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class VentaController {

    @Autowired
    private VentaService service;

    @GetMapping
    public ResponseEntity<List<Venta>> listaTodos() {
        return ResponseEntity.ok(service.listaTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscaPorId(@PathVariable Integer id) {
        Optional<Venta> opt = service.buscaPorId(id);
        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        }
        HashMap<String, Object> salida = new HashMap<>();
        salida.put("error", "Registro no encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(salida);
    }

    @PostMapping
    public ResponseEntity<?> registro(@RequestBody Venta obj) {
        HashMap<String, Object> salida = new HashMap<>();
        Venta objSalida = service.inserta(obj);
        salida.put("data", objSalida);
        salida.put("mensaje", "Registro exitoso");
        return ResponseEntity.ok(salida);
    }

    @PutMapping
    public ResponseEntity<?> actualiza(@RequestBody Venta obj) {
        HashMap<String, Object> salida = new HashMap<>();
        if (obj.getIdVenta() == null || service.buscaPorId(obj.getIdVenta()).isEmpty()) {
            salida.put("error", "El registro no existe: envía el campo idVenta en el JSON");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(salida);
        }
        Venta objSalida = service.actualiza(obj);
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
