package com.josemaba.kosmos.medicos.kosmosmedicos.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.josemaba.kosmos.medicos.kosmosmedicos.entity.Cita;
import com.josemaba.kosmos.medicos.kosmosmedicos.entity.Doctor;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    // @Query("SELECT c FROM Cita c WHERE c.doctor.id = :doctorId AND c.fechaHora = :fechaHora")
    // boolean existsByDoctorAndFechaHora(@Param("doctorId") Long doctorId, @Param("fechaHora") LocalDateTime fechaHora);

    boolean existsByDoctorAndFechaHora(Doctor doctor, LocalDateTime fechaHora);
    Page<Cita> findAllByDoctor(Doctor doctor, Pageable pageable);
    Page<Cita> findAllByFechaHoraGreaterThanEqualAndFechaHoraLessThan(LocalDateTime startOfDay, LocalDateTime endOfDay, Pageable pageable);

}