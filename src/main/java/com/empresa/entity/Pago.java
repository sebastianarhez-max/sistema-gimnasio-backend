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
@Table(name = "pago")
@Getter
@Setter
@NoArgsConstructor
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPago;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idMembresia", nullable = false)
    private Membresia membresia;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idTipoPago", nullable = false)
    private TipoPago tipoPago;
    private Double monto;
    private LocalDateTime fecha = LocalDateTime.now();
    private String concepto;
}
