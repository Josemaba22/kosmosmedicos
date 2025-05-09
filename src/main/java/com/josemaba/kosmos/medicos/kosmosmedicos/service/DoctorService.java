package com.josemaba.kosmos.medicos.kosmosmedicos.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.josemaba.kosmos.medicos.kosmosmedicos.dto.DoctorDTO;
import com.josemaba.kosmos.medicos.kosmosmedicos.entity.Doctor;

public interface DoctorService {
    Page<Doctor> findAll(Pageable pageable);
    Optional<Doctor> findById(Long id);
    Doctor save(DoctorDTO doctorDTO);
    Doctor update(DoctorDTO doctorDTO);
    Doctor deleteById(Long id);
}