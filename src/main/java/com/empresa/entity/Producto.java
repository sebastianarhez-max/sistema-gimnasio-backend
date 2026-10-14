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
@Table(name = "producto")
@Getter
@Setter
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idCategoria", nullable = false)
    private CategoriaProducto categoria;
    private String nombre;
    private Double precio;
    private Integer stock = 0;
    private Integer stockMinimo = 5;
    private Boolean estado = true;
}
