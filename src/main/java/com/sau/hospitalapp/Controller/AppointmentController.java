package com.sau.hospitalapp.Controller;

import com.sau.hospitalapp.Dto.AppointmentDto;
import com.sau.hospitalapp.Dto.CreateAppointmentDto;
import com.sau.hospitalapp.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/registers")
public class
AppointmentController {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping()
    public ResponseEntity<?> showRegisterList() {
        List<AppointmentDto> registrations = appointmentService.getAll();
        return ResponseEntity.ok(registrations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try {
            AppointmentDto appointmentDto = appointmentService.getById(id);
            return ResponseEntity.ok(appointmentDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRegister(@RequestBody CreateAppointmentDto dto){
        try {
            AppointmentDto newAppointment = appointmentService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(newAppointment);
        } catch (Exception e) {
            e.printStackTrace(); // Hata detaylarını log'a yazdır
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Appointment could not be added! Error: " + e.getMessage());
        }
    }


    @PostMapping("/update/{id}")
    public ResponseEntity<?> registerUpdate(@PathVariable Long id, @RequestBody CreateAppointmentDto dto){
        try {
            AppointmentDto updatedStudentLecture = appointmentService.update(id, dto);
            return ResponseEntity.ok(updatedStudentLecture);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> registerDelete(@PathVariable Long id){
        try {
            appointmentService.delete(id);
            return ResponseEntity.ok("Appointment deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
