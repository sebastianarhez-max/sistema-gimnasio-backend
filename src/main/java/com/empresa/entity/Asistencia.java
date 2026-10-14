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
@Table(name = "asistencia")
@Getter
@Setter
@NoArgsConstructor
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAsistencia;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idHorario", nullable = false)
    private Horario horario;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;
    private LocalDate fecha;
    private LocalDateTime horaIngreso;
    @Enumerated(EnumType.STRING)
    private EstadoAsistencia estado = EstadoAsistencia.RESERVADO;
    @ManyToOne
    @JoinColumn(name = "idTipoPago")
    private TipoPago tipoPago;
    private BigDecimal montoPagado = BigDecimal.ZERO;
}
