package com.josemaba.kosmos.medicos.kosmosmedicos.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor
public class CitaDTO {
    private Long id;

    @NotNull(message = "La fecha y hora de la cita no pueden ser nulas")
    private LocalDateTime fechaHora;
    
    private Long id_doctor;
    
    private String nombrePaciente;
}
