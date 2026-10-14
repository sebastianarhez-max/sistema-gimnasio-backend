package com.empresa.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;

@Entity
@Table(name = "rutina")
@Getter
@Setter
@NoArgsConstructor
public class Rutina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRutina;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idEntrenador", nullable = false)
    private Entrenador entrenador;
    private String nombre;
    private String objetivo;
    @JdbcTypeCode(SqlTypes.JSON)
    private List<Map<String, Object>> ejercicios;
    private LocalDate fechaCreacion = LocalDate.now();
    private Boolean estado = true;
}
