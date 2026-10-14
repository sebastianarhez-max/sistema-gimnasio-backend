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
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {
    @Query("SELECT new com.empresa.dto.ProductoVendidoDTO(p.nombre, SUM(d.cantidad), SUM(d.subtotal)) FROM DetalleVenta d "
            + "JOIN d.producto p GROUP BY p.nombre ORDER BY SUM(d.cantidad) DESC")
    List<ProductoVendidoDTO> reporteProductosVendidos();
}
