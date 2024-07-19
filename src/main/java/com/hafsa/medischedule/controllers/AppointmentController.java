package com.hafsa.medischedule.controllers;

import com.hafsa.medischedule.model.Appointment;
import com.hafsa.medischedule.model.User;
import com.hafsa.medischedule.services.AppointmentService;
import com.hafsa.medischedule.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/appointment")
public class AppointmentController {


    private final UserService userService;
    private final AppointmentService appointmentService;

    public AppointmentController(UserService userService, AppointmentService appointmentService) {
        this.userService = userService;
        this.appointmentService = appointmentService;
    }

    @PostMapping("/add")
    public ResponseEntity<Appointment> createApp(@RequestBody Appointment appointment) {
        Optional<User> user = userService.getUserById(appointment.getUser().getId());

        if ( user.isPresent()) {
            appointment.setUser(user.get());
            Appointment saveApp =  appointmentService.saveAppoint(appointment);
            return new ResponseEntity<>(saveApp, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/all")

    public ResponseEntity<List<Appointment>> getAllApp()
    {
        List<Appointment> appointments = appointmentService.getAllApp();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<String>("user deleted successfully",HttpStatus.NO_CONTENT);
    }



}
