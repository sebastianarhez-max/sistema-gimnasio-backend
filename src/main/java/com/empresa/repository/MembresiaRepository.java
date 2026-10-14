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
public interface MembresiaRepository extends JpaRepository<Membresia, Integer> {
    boolean existsByCliente_IdClienteAndEstadoTrueAndFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(Integer idCliente, LocalDate desde, LocalDate hasta);

    @Query("SELECT new com.empresa.dto.MembresiaPlanDTO(p.nombre, COUNT(m)) FROM Membresia m JOIN m.plan p "
            + "WHERE m.estado = true GROUP BY p.nombre ORDER BY COUNT(m) DESC")
    List<MembresiaPlanDTO> reportePorPlan();
}
