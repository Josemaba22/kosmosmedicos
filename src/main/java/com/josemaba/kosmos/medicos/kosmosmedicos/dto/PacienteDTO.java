package com.josemaba.kosmos.medicos.kosmosmedicos.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDTO {
    private Long id;

    @NotBlank
    private String nombre;
    
    @NotBlank
    private LocalDate fechaNacimiento;
    
    @Email
    private String email;
}

