package com.hafsa.medischedule.services;

import com.hafsa.medischedule.model.Appointment;
import com.hafsa.medischedule.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public Appointment saveAppoint(Appointment appointment) {
        return repository.save(appointment);
    }

    public List<Appointment> getAllApp() {
        return repository.findAll();
    }
}
