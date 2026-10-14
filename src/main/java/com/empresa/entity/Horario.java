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
@Table(name = "horario")
@Getter
@Setter
@NoArgsConstructor
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHorario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idActividad", nullable = false)
    private Actividad actividad;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idEntrenador", nullable = false)
    private Entrenador entrenador;
    private String dia;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Integer cupoMaximo = 20;
    @Enumerated(EnumType.STRING)
    private TipoAcceso tipoAcceso = TipoAcceso.MEMBRESIA;
    private BigDecimal precioEntrada = BigDecimal.ZERO;
    private Boolean estado = true;
}
