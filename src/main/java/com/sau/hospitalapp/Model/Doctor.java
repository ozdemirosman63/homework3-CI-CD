package com.sau.hospitalapp.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Table(name = "doctors")
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String department;
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Appointment> patientDoctors;

    public Doctor(){};
    public Doctor(Long id, String name, String department) {
        this.id=id;
        this.name=name;
        this.department=department;
    }

}
