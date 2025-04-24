package com.sau.hospitalapp.Controller;

import com.sau.hospitalapp.Dto.PatientDto;
import com.sau.hospitalapp.Model.Patient;
import com.sau.hospitalapp.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping()
    public ResponseEntity<List<PatientDto>> getAll(){
        List<PatientDto> patients = patientService.getAll();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try {
            PatientDto patients = patientService.getPatientById(id);
            return ResponseEntity.ok(patients);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPatient(@RequestBody Patient patient){
        try {
            Patient newPatient = patientService.save(patient);
            return ResponseEntity.status(HttpStatus.CREATED).body(newPatient);
        } catch (Exception e) {
            // Daha ayrıntılı hata mesajı
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Patient could not be added! Error: " + e.getMessage());
        }
    }


    @PostMapping("/update/{id}")
    public ResponseEntity<?> patientUpdate(@PathVariable Long id, @RequestBody Patient patients){
        try {
            PatientDto updatedPatient = patientService.update(id, patients);
            return ResponseEntity.ok(updatedPatient);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> patientDelete(@PathVariable Long id){
        try {
            patientService.delete(id);
            return ResponseEntity.ok("patients deleted succcessfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
