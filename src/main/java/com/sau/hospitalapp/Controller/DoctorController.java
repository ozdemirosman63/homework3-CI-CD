package com.sau.hospitalapp.Controller;

import com.sau.hospitalapp.Dto.DoctorDto;
import com.sau.hospitalapp.Model.Doctor;
import com.sau.hospitalapp.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping()
    public ResponseEntity<List<DoctorDto>> getAll(){
        List<DoctorDto> doctor = doctorService.getAll();
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try {
            DoctorDto student = doctorService.getDoctorById(id);
            return ResponseEntity.ok(student);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody Doctor student){
        try {
            Doctor newStudent = doctorService.save(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student could not be added!");
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> doctorUpdate(@PathVariable Long id, @RequestBody Doctor doctor){
        try {
            DoctorDto updatedDoctor = doctorService.update(id, doctor);
            return ResponseEntity.ok(updatedDoctor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> doctorDelete(@PathVariable Long id){
        try {
            doctorService.delete(id);
            return ResponseEntity.ok("Student deleted succcessfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
