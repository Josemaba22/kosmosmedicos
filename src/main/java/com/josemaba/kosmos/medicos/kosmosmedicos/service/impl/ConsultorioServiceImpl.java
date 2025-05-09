package com.josemaba.kosmos.medicos.kosmosmedicos.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.josemaba.kosmos.medicos.kosmosmedicos.dto.ConsultorioDTO;
import com.josemaba.kosmos.medicos.kosmosmedicos.entity.Consultorio;
import com.josemaba.kosmos.medicos.kosmosmedicos.exception.ObjectNotFoundException;
import com.josemaba.kosmos.medicos.kosmosmedicos.repository.ConsultorioRepository;
import com.josemaba.kosmos.medicos.kosmosmedicos.service.ConsultorioService;

@Service
public class ConsultorioServiceImpl implements ConsultorioService{

    @Autowired
    private ConsultorioRepository consultorioRepository;

    @Override
    public Consultorio deleteById(Long id) {
        Consultorio consultorio = consultorioRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException("No se encotró la Consultorio."));
        consultorioRepository.deleteById(id);
        return consultorio;
    }

    @Override
    public Page<Consultorio> findAll(Pageable pageable) {
        return consultorioRepository.findAll(pageable);
    }

    @Override
    public Optional<Consultorio> findById(Long id) {
        return consultorioRepository.findById(id);
    }

    @Override
    public Consultorio save(ConsultorioDTO consultorioDTO) {
        Consultorio consultorio = new Consultorio();
        consultorio.setNumeroDeConsultorio(consultorioDTO.getNumeroDeConsultorio());
        consultorio.setPiso(consultorioDTO.getPiso());
        return consultorioRepository.save(consultorio);
    }

    @Override
    public Consultorio update(ConsultorioDTO consultorioDTO) {
        Consultorio consultorio = consultorioRepository.findById(consultorioDTO.getId())
            .orElseThrow(() -> new ObjectNotFoundException("No se encontró el consultorio para actualizar."));
        consultorio.setNumeroDeConsultorio(consultorioDTO.getNumeroDeConsultorio());
        consultorio.setPiso(consultorioDTO.getPiso());
        return consultorioRepository.save(consultorio);
    }
    
}
