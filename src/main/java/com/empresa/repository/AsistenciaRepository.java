package com.empresa.repository;

import com.empresa.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empresa.dto.*;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Integer> {
    long countByHorario_IdHorarioAndFechaAndEstadoNot(Integer idHorario, LocalDate fecha, EstadoAsistencia estado);
    Optional<Asistencia> findByHorario_IdHorarioAndCliente_IdClienteAndFecha(Integer idHorario, Integer idCliente, LocalDate fecha);

    @Query("SELECT new com.empresa.dto.AsistenciaEstadoDTO(a.estado, COUNT(a)) FROM Asistencia a "
            + "WHERE a.horario.idHorario = :idHorario AND a.fecha = :fecha GROUP BY a.estado")
    List<AsistenciaEstadoDTO> reportePorEstado(@Param("idHorario") Integer idHorario, @Param("fecha") LocalDate fecha);

    @Query("SELECT new com.empresa.dto.ClaseConcurrenciaDTO(act.nombre, COUNT(a)) FROM Asistencia a "
            + "JOIN a.horario h JOIN h.actividad act WHERE a.estado = :estado GROUP BY act.nombre ORDER BY COUNT(a) DESC")
    List<ClaseConcurrenciaDTO> reporteClasesConcurridas(@Param("estado") EstadoAsistencia estado);
}
