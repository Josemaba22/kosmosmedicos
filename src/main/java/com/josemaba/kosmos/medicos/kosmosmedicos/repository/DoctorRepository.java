package com.josemaba.kosmos.medicos.kosmosmedicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.josemaba.kosmos.medicos.kosmosmedicos.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
