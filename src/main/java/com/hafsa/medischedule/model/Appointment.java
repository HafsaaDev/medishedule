package com.hafsa.medischedule.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appointment")

public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "app_date")
    private LocalDate date;

    @Column(name = "status")
    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userID")
    private User user;
}
