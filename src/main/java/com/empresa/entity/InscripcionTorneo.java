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
@Table(name = "inscripcion_torneo")
@Getter
@Setter
@NoArgsConstructor
public class InscripcionTorneo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInscripcion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idTorneo", nullable = false)
    private Torneo torneo;
    @ManyToOne
    @JoinColumn(name = "idEntrenador")
    private Entrenador entrenador;
    private String categoria;
    private String sexo;
    private BigDecimal pesoMinimo;
    private BigDecimal pesoMaximo;
    private LocalDateTime fechaInscripcion = LocalDateTime.now();
    private Integer puesto;
    private BigDecimal puntaje;
    private String observaciones;
    private Boolean estado = true;
}
