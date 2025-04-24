package com.sau.hospitalapp.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Table(name = "appointments")
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date appointment_date;

    private String appointment_type;
    @ManyToOne()
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @ManyToOne()
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;


}
