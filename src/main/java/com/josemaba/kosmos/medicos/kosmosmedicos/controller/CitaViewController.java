package com.josemaba.kosmos.medicos.kosmosmedicos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.josemaba.kosmos.medicos.kosmosmedicos.entity.Cita;


@Controller
public class CitaViewController {
    
    @GetMapping("/")
    public String hello() {
        return "index";
    }

    @GetMapping("/cita/nueva")
    public String getMethodName(Model modelo) {
        //modelo.addAttribute("cita", new Cita());
        return "crearCita";
    }
    
}
