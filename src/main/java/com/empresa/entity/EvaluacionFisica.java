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
@Table(name = "evaluacion_fisica")
@Getter
@Setter
@NoArgsConstructor
public class EvaluacionFisica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvaluacion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;
    private LocalDate fecha = LocalDate.now();
    private BigDecimal peso;
    private BigDecimal altura;
    private BigDecimal imc;
    private BigDecimal grasaCorporal;
    private String observaciones;
}
