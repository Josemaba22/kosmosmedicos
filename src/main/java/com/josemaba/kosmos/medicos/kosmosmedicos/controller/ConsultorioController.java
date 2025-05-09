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

import com.josemaba.kosmos.medicos.kosmosmedicos.dto.ConsultorioDTO;
import com.josemaba.kosmos.medicos.kosmosmedicos.entity.Consultorio;
import com.josemaba.kosmos.medicos.kosmosmedicos.service.ConsultorioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/consultorio") // Usa singular "consultorio" para la convención RESTful
public class ConsultorioController {

    @Autowired
    private ConsultorioService consultorioService;

    @GetMapping("/{id}")
    public ResponseEntity<Consultorio> findById(@PathVariable Long id) {
        return consultorioService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<Consultorio>> findAll(Pageable pageable) {
        Page<Consultorio> consultorios = consultorioService.findAll(pageable);
        if (consultorios.hasContent()) {
            return ResponseEntity.ok(consultorios);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Consultorio> deleteById(@PathVariable Long id) {
        Consultorio consultorio = consultorioService.deleteById(id);
        if (consultorio != null) {
            return ResponseEntity.ok(consultorio);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public ResponseEntity<Consultorio> save(@RequestBody @Valid ConsultorioDTO request) {
        Consultorio consultorio = consultorioService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(consultorio);
    }

    @PutMapping("/update")
    public ResponseEntity<Consultorio> update(@RequestBody @Valid ConsultorioDTO request) {
        Consultorio consultorio = consultorioService.update(request);
        return ResponseEntity.status(HttpStatus.OK).body(consultorio);
    }
}