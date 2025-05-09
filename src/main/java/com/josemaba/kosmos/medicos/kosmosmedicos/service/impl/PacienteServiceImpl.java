package com.josemaba.kosmos.medicos.kosmosmedicos.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.josemaba.kosmos.medicos.kosmosmedicos.dto.PacienteDTO;
import com.josemaba.kosmos.medicos.kosmosmedicos.entity.Paciente;
import com.josemaba.kosmos.medicos.kosmosmedicos.exception.ObjectNotFoundException;
import com.josemaba.kosmos.medicos.kosmosmedicos.repository.PacienteRepository;
import com.josemaba.kosmos.medicos.kosmosmedicos.service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;
    @Override
    public Paciente deleteById(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException("No se encontró el paciente"));
        pacienteRepository.deleteById(id);
        return paciente;
    }

    @Override
    public Page<Paciente> findAll(Pageable pageable) {
        return pacienteRepository.findAll(pageable);
    }

    @Override
    public Optional<Paciente> findById(Long id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public Paciente save(PacienteDTO pacienteDTO) {
        Paciente paciente = new Paciente();
        paciente.setNombre(pacienteDTO.getNombre());
        paciente.setFechaNacimiento(pacienteDTO.getFechaNacimiento());
        paciente.setEmail(pacienteDTO.getEmail());
        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente update(PacienteDTO pacienteDTO) {
        Paciente paciente = pacienteRepository.findById(pacienteDTO.getId())
            .orElseThrow(() -> new ObjectNotFoundException("No se encontró el paciente para actualizar."));
        paciente.setNombre(pacienteDTO.getNombre());
        paciente.setFechaNacimiento(pacienteDTO.getFechaNacimiento());
        paciente.setEmail(pacienteDTO.getEmail());
        return pacienteRepository.save(paciente);
    }
    
    
}
