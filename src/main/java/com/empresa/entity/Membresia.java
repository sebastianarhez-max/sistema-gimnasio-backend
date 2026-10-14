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
@Table(name = "membresia")
@Getter
@Setter
@NoArgsConstructor
public class Membresia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMembresia;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idPlan", nullable = false)
    private Plan plan;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Boolean estado = true;
}
