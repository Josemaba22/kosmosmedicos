package com.josemaba.kosmos.medicos.kosmosmedicos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josemaba.kosmos.medicos.kosmosmedicos.dto.CitaDTO;
import com.josemaba.kosmos.medicos.kosmosmedicos.entity.Cita;
import com.josemaba.kosmos.medicos.kosmosmedicos.service.CitaService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/cita")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping("/{id}")
    public ResponseEntity<Cita> findById(@PathVariable Long id) {
        return citaService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<Cita>> findAll(Pageable pageable) {
        Page<Cita> citas = citaService.findAll(pageable);
        if (citas.hasContent()) {
            return ResponseEntity.ok(citas);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Cita> deleteById(@PathVariable Long id) {
        Cita cita = citaService.deleteById(id);
        if (cita != null) {
            return ResponseEntity.ok(cita);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public ResponseEntity<Cita> save(@RequestBody @Valid CitaDTO request) {
        Cita cita = citaService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(cita);
    }

    @PutMapping("/update")
    public ResponseEntity<Cita> update(@RequestBody @Valid CitaDTO request) {
        Cita cita = citaService.update(request);
        return ResponseEntity.status(HttpStatus.OK).body(cita);
    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<Page<Cita>> findByDoctor(@PathVariable Long id, Pageable pageable) {
        Page<Cita> citas = citaService.findAllByDoctor(id, pageable);
        if (citas.hasContent()) {
            return ResponseEntity.ok(citas);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/hoy")
    public ResponseEntity<Page<Cita>> findAllCitasDelDiaActual(Pageable pageable) {
        Page<Cita> citas = citaService.findAllCitasDelDiaActual(pageable);
        if (citas.hasContent()) {
            return ResponseEntity.ok(citas);
        }
        return ResponseEntity.notFound().build();
    }
    
    
}
