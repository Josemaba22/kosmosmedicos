package com.josemaba.kosmos.medicos.kosmosmedicos.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.josemaba.kosmos.medicos.kosmosmedicos.dto.ConsultorioDTO;
import com.josemaba.kosmos.medicos.kosmosmedicos.entity.Consultorio;

public interface ConsultorioService {
    Page<Consultorio> findAll(Pageable pageable);
    Optional<Consultorio> findById(Long id);
    Consultorio save(ConsultorioDTO ConsultorioDTO);
    Consultorio update(ConsultorioDTO ConsultorioDTO);
    Consultorio deleteById(Long id);
    
}