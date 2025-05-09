package com.josemaba.kosmos.medicos.kosmosmedicos.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.josemaba.kosmos.medicos.kosmosmedicos.dto.PacienteDTO;
import com.josemaba.kosmos.medicos.kosmosmedicos.entity.Paciente;

public interface PacienteService {
    Page<Paciente> findAll(Pageable pageable);
    Optional<Paciente> findById(Long id);
    Paciente save(PacienteDTO pacienteDTO);
    Paciente update(PacienteDTO pacienteDTO);
    Paciente deleteById(Long id);
}