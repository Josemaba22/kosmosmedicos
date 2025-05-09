package com.josemaba.kosmos.medicos.kosmosmedicos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(HorarioOcupadoException.class)
    public ResponseEntity<String> handleHorarioOcupado(HorarioOcupadoException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(CitaEnPasadoException.class)
    public ResponseEntity<String> handleCitaEnPasado(CitaEnPasadoException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
