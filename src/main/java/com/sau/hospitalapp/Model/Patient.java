package com.sau.hospitalapp.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String address;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Appointment> patientDoctors;

    public Patient(){};
    public Patient(Long id, String name, String surname,String address) {
        this.id=id;
        this.name = name;
        this.surname=surname;
        this.address=address;
    }
}
