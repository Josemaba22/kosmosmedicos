package com.josemaba.kosmos.medicos.kosmosmedicos.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.josemaba.kosmos.medicos.kosmosmedicos.dto.CitaDTO;
import com.josemaba.kosmos.medicos.kosmosmedicos.entity.Cita;

public interface CitaService {
    Page<Cita> findAll(Pageable pageable);
    Optional<Cita> findById(Long id);
    Cita save(CitaDTO citaDTO);
    Cita update(CitaDTO citaDTO);
    Cita deleteById(Long id);
    Page<Cita> findAllByDoctor(Long id, Pageable pageable);
    Page<Cita> findAllCitasDelDiaActual(Pageable pageable);
}
