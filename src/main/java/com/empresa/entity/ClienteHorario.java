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
@Table(name = "cliente_horario")
@Getter
@Setter
@NoArgsConstructor
public class ClienteHorario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClienteHorario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idHorario", nullable = false)
    private Horario horario;
    private LocalDateTime fechaInscripcion = LocalDateTime.now();
    private Boolean estado = true;
}
