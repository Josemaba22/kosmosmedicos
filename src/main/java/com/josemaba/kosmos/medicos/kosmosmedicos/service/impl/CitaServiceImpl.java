package com.josemaba.kosmos.medicos.kosmosmedicos.service.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.josemaba.kosmos.medicos.kosmosmedicos.dto.CitaDTO;
import com.josemaba.kosmos.medicos.kosmosmedicos.entity.Cita;
import com.josemaba.kosmos.medicos.kosmosmedicos.entity.Doctor;
import com.josemaba.kosmos.medicos.kosmosmedicos.exception.CitaEnPasadoException;
import com.josemaba.kosmos.medicos.kosmosmedicos.exception.HorarioOcupadoException;
import com.josemaba.kosmos.medicos.kosmosmedicos.exception.HorarioPacienteInvalidoException;
import com.josemaba.kosmos.medicos.kosmosmedicos.exception.MaximoDeCitasException;
import com.josemaba.kosmos.medicos.kosmosmedicos.exception.ObjectNotFoundException;
import com.josemaba.kosmos.medicos.kosmosmedicos.repository.CitaRepository;
import com.josemaba.kosmos.medicos.kosmosmedicos.repository.DoctorRepository;
// import com.josemaba.kosmos.medicos.kosmosmedicos.repository.PacienteRepository;
import com.josemaba.kosmos.medicos.kosmosmedicos.service.CitaService;

@Service
public class CitaServiceImpl implements CitaService {
    
    @Autowired
    private CitaRepository citaRepository;

    // @Autowired
    // private PacienteRepository pacienteRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Cita deleteById(Long id) {
        Cita cita = citaRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException("No se encotró la cita."));
        if(cita.getFechaHora().isBefore(LocalDateTime.now())) {
            throw new CitaEnPasadoException("No se puede cancerlar una cita ya pasada.");
        }
        citaRepository.deleteById(id);
        return cita;
    }

    @Override
    public Page<Cita> findAll(Pageable pageable) {
        return citaRepository.findAll(pageable);
    }

    @Override
    public Optional<Cita> findById(Long id) {
        return citaRepository.findById(id);
    }

    @Override
    public Cita save(CitaDTO citaDTO) {
    
        if(citaDTO.getFechaHora().isBefore(LocalDateTime.now())){
            throw new CitaEnPasadoException("La fecha ya expiró, ingrese una nueva fecha");
        }
        Cita cita = new Cita();
        cita.setFechaHora(citaDTO.getFechaHora());
        cita.setNombrePaciente(citaDTO.getNombrePaciente());
        Doctor doctor = doctorRepository.findById(citaDTO.getId_doctor()).get();

        boolean estaHoraOcupada = citaRepository.existsByDoctorAndFechaHora(doctor, citaDTO.getFechaHora());
        if(estaHoraOcupada){
            throw new HorarioOcupadoException("Horario ocupado, por favor, ingrsese otra fecha");
        }

        LocalDate fecha = cita.getFechaHora().toLocalDate();
        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.atTime(23,59,59);
        int countrCitasDelDia = citaRepository.countByDoctorIdAndFechaHoraBetween(cita.getDoctor(), inicio, fin);
        if(countrCitasDelDia >= 8){
            throw new MaximoDeCitasException("El doctor ya tiene 8 citas para ese día");
        }

        List<Cita> citasDelPaciente = citaRepository.findByNombrePacienteAndFechaHoraBetween(cita.getNombrePaciente(), inicio, fin);
        for(Cita existente : citasDelPaciente){
            Duration diferencia = Duration.between(existente.getFechaHora(), cita.getFechaHora()).abs();
            if(diferencia.toHours() < 2){
                throw new HorarioPacienteInvalidoException("El paciente ya tiene cita en horario cercano.");
            }
        }

        cita.setDoctor(doctor);
        return citaRepository.save(cita);
    
    }

    @Override
    public Cita update(CitaDTO citaDTO) {
        
        Cita cita = citaRepository.findById(citaDTO.getId())
            .orElseThrow(() -> new ObjectNotFoundException("No se encontró la cita."));

        cita.setFechaHora(citaDTO.getFechaHora());
        cita.setNombrePaciente(citaDTO.getNombrePaciente());
        Doctor doctor = doctorRepository.findById(citaDTO.getId_doctor()).get();
            
        boolean estaHoraOcupada = citaRepository.existsByDoctorAndFechaHora(doctor, citaDTO.getFechaHora());
        if(estaHoraOcupada){
            throw new HorarioOcupadoException("Horario ocupado, por favor, ingrsese otra fecha");
        }

        if(citaDTO.getFechaHora().isBefore(LocalDateTime.now())){
            throw new CitaEnPasadoException("La fecha ya expiró, ingrese una nueva fecha");
        }

        LocalDate fecha = cita.getFechaHora().toLocalDate();
        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.atTime(23,59,59);
        int countrCitasDelDia = citaRepository.countByDoctorIdAndFechaHoraBetween(cita.getDoctor(), inicio, fin);
        if(countrCitasDelDia >= 8){
            throw new MaximoDeCitasException("El doctor ya tiene 8 citas para ese día");
        }

        List<Cita> citasDelPaciente = citaRepository.findByNombrePacienteAndFechaHoraBetween(cita.getNombrePaciente(), inicio, fin);
        for(Cita existente : citasDelPaciente){
            Duration diferencia = Duration.between(existente.getFechaHora(), cita.getFechaHora()).abs();
            if(diferencia.toHours() < 2){
                throw new HorarioPacienteInvalidoException("El paciente ya tiene cita en horario cercano.");
            }
        }

        cita.setDoctor(doctor);
        return citaRepository.save(cita);
    
    }

    @Override
    public Page<Cita> findAllByDoctor(Long id, Pageable pageable){
        Doctor doctor = doctorRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException("No se encontró doctor"));

        return citaRepository.findAllByDoctor(doctor, pageable);
    }

    @Override
    public Page<Cita> findAllCitasDelDiaActual(Pageable pageable) {
        // Obtener la fecha actual en la zona horaria de Culiacán
        ZoneId culiacanZone = ZoneId.of("America/Mazatlan");
        LocalDate today = LocalDate.now(culiacanZone);

        // Definir el inicio y el fin del día
        LocalDateTime startOfDay = today.atStartOfDay(culiacanZone).toLocalDateTime();
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX).atZone(culiacanZone).toLocalDateTime();

        return citaRepository.findAllByFechaHoraGreaterThanEqualAndFechaHoraLessThan(
                startOfDay,
                endOfDay,
                pageable
        );
    }

    

}
