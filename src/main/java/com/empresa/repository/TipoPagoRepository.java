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
public interface TipoPagoRepository extends JpaRepository<TipoPago, Integer> {
}
