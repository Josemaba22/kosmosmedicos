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

import com.josemaba.kosmos.medicos.kosmosmedicos.dto.PacienteDTO;
import com.josemaba.kosmos.medicos.kosmosmedicos.entity.Paciente;
import com.josemaba.kosmos.medicos.kosmosmedicos.service.PacienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> findById(@PathVariable Long id) {
        return pacienteService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<Paciente>> findAll(Pageable pageable) {
        Page<Paciente> pacientes = pacienteService.findAll(pageable);
        if (pacientes.hasContent()) {
            return ResponseEntity.ok(pacientes);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Paciente> deleteById(@PathVariable Long id) {
        Paciente paciente = pacienteService.deleteById(id);
        if (paciente != null) {
            return ResponseEntity.ok(paciente);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public ResponseEntity<Paciente> save(@RequestBody @Valid PacienteDTO request) {
        Paciente paciente = pacienteService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
    }

    @PutMapping("/update")
    public ResponseEntity<Paciente> update(@RequestBody @Valid PacienteDTO request) {
        Paciente paciente = pacienteService.update(request);
        return ResponseEntity.status(HttpStatus.OK).body(paciente);
    }
}
