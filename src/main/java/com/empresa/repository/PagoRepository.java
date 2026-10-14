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
public interface PagoRepository extends JpaRepository<Pago, Integer> {
    @Query("SELECT new com.empresa.dto.IngresoMetodoPagoDTO(tp.nombre, COUNT(p), SUM(p.monto)) FROM Pago p "
            + "JOIN p.tipoPago tp WHERE p.fecha >= :desde AND p.fecha < :hasta GROUP BY tp.nombre ORDER BY SUM(p.monto) DESC")
    List<IngresoMetodoPagoDTO> reportePorMetodoPago(@Param("desde") LocalDateTime desde, @Param("hasta") LocalDateTime hasta);
}
