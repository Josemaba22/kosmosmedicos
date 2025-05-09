package com.josemaba.kosmos.medicos.kosmosmedicos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josemaba.kosmos.medicos.kosmosmedicos.dto.DoctorDTO;
import com.josemaba.kosmos.medicos.kosmosmedicos.entity.Doctor;
import com.josemaba.kosmos.medicos.kosmosmedicos.service.DoctorService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/doctor")
public class DoctorController {
    
    @Autowired
    private DoctorService doctorService;  

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> findById(@PathVariable Long id) {
        return doctorService.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<Doctor>> findAll(Pageable pageable) {
        Page<Doctor> doctors = doctorService.findAll(pageable);
        if (doctors.hasContent()) {
            return ResponseEntity.ok(doctors);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Doctor> deleteById(@PathVariable Long id) {
        Doctor doctor = doctorService.deleteById(id);
        if (doctor != null) {
            return ResponseEntity.ok(doctor);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public ResponseEntity<Doctor> save(@RequestBody @Valid DoctorDTO request) {
        Doctor doctor = doctorService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(doctor);
    }  

    @PutMapping("/update")
    public ResponseEntity<Doctor> update(@RequestBody @Valid DoctorDTO request) {
        Doctor doctor = doctorService.update(request);
        return ResponseEntity.status(HttpStatus.OK).body(doctor);
    }
    

}