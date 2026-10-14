package com.empresa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.empresa.entity.ClienteHorario;
import com.empresa.service.ClienteHorarioService;
import com.empresa.util.AppSettings;

@RestController
@RequestMapping("/url/clientehorario")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ClienteHorarioController {

    @Autowired
    private ClienteHorarioService service;

    @GetMapping
    public ResponseEntity<List<ClienteHorario>> listaTodos() {
        return ResponseEntity.ok(service.listaTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscaPorId(@PathVariable Integer id) {
        Optional<ClienteHorario> opt = service.buscaPorId(id);
        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        }
        HashMap<String, Object> salida = new HashMap<>();
        salida.put("error", "Registro no encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(salida);
    }

    @PostMapping
    public ResponseEntity<?> registro(@RequestBody ClienteHorario obj) {
        HashMap<String, Object> salida = new HashMap<>();
        ClienteHorario objSalida = service.inserta(obj);
        salida.put("data", objSalida);
        salida.put("mensaje", "Registro exitoso");
        return ResponseEntity.ok(salida);
    }

    @PutMapping
    public ResponseEntity<?> actualiza(@RequestBody ClienteHorario obj) {
        HashMap<String, Object> salida = new HashMap<>();
        if (obj.getIdClienteHorario() == null || service.buscaPorId(obj.getIdClienteHorario()).isEmpty()) {
            salida.put("error", "El registro no existe: envía el campo idClienteHorario en el JSON");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(salida);
        }
        ClienteHorario objSalida = service.actualiza(obj);
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
