package com.empresa.service;

import java.util.Map;

import com.empresa.dto.AsistenciaRegistroDTO;
import com.empresa.dto.MembresiaRegistroDTO;
import com.empresa.dto.VentaRegistroDTO;
import com.empresa.entity.Asistencia;

public interface RegistroService {

    Map<String, Object> registrarMembresia(MembresiaRegistroDTO dto);

    Map<String, Object> registrarVenta(VentaRegistroDTO dto);

    Asistencia reservar(AsistenciaRegistroDTO dto);

    Asistencia registrarIngreso(AsistenciaRegistroDTO dto);

    Asistencia marcarFalto(Integer idAsistencia);
}
