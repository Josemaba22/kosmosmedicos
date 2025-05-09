package com.josemaba.kosmos.medicos.kosmosmedicos.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.josemaba.kosmos.medicos.kosmosmedicos.dto.DoctorDTO;
import com.josemaba.kosmos.medicos.kosmosmedicos.entity.Doctor;
import com.josemaba.kosmos.medicos.kosmosmedicos.exception.ObjectNotFoundException;
import com.josemaba.kosmos.medicos.kosmosmedicos.repository.DoctorRepository;
import com.josemaba.kosmos.medicos.kosmosmedicos.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {
    
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Doctor deleteById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException("Doctor no encontrado."));
        doctorRepository.deleteById(id);
        return doctor;
    }
    
    @Override
    public Page<Doctor> findAll(Pageable pageable) {
        return doctorRepository.findAll(pageable);
    }

    @Override
    public Optional<Doctor> findById(Long id) {
        return doctorRepository.findById(id);
    }

    @Override
    public Doctor save(DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setNombre(doctorDTO.getNombre());
        doctor.setApellidoPaterno(doctorDTO.getApellidoPaterno());
        doctor.setApellidoMaterno(doctorDTO.getApellidoMaterno());
        doctor.setEspecialidad(doctorDTO.getEspecialidad());
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor update(DoctorDTO doctorDTO) {
        Doctor doctor = doctorRepository.findById(doctorDTO.getId())
            .orElseThrow(() -> new ObjectNotFoundException("No se encontró doctor."));
        doctor.setNombre(doctorDTO.getNombre());
        doctor.setApellidoPaterno(doctorDTO.getApellidoPaterno());
        doctor.setApellidoMaterno(doctorDTO.getApellidoMaterno());
        doctor.setEspecialidad(doctorDTO.getEspecialidad());
        return doctorRepository.save(doctor);
    }

    

    

}
