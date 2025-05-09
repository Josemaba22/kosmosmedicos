package com.josemaba.kosmos.medicos.kosmosmedicos.repository;

import org.springframework.stereotype.Repository;

import com.josemaba.kosmos.medicos.kosmosmedicos.entity.Consultorio;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ConsultorioRepository extends JpaRepository<Consultorio, Long>{
    
}
