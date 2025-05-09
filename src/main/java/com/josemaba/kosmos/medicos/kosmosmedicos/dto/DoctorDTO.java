package com.josemaba.kosmos.medicos.kosmosmedicos.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor
public class DoctorDTO {
    private Long id;

    @NotBlank
    private String nombre;
    
    @NotBlank
    private String especialidad;

    @NotBlank
    private String consultorio;
}
