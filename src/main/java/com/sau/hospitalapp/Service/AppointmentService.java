package com.sau.hospitalapp.Service;

import com.sau.hospitalapp.Dto.AppointmentDto;
import com.sau.hospitalapp.Dto.CreateAppointmentDto;
import com.sau.hospitalapp.Exception.ResourceNotFoundException;
import com.sau.hospitalapp.Model.Appointment;
import com.sau.hospitalapp.Model.Doctor;
import com.sau.hospitalapp.Model.Patient;
import com.sau.hospitalapp.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository repository;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    AppointmentDto appointmentDto = new AppointmentDto();

    public List<AppointmentDto> getAll(){
        List<Appointment> appointments = repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return appointments.stream()
                .map(sl -> appointmentDto.convertToDTO(sl))
                .collect(Collectors.toList());
    }

    public AppointmentDto getById(Long id){
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StudentLecture not found with id: " + id));
        return appointmentDto.convertToDTO(appointment);
    }

    public Appointment findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StudentLecture not found with id: " + id));
    }
    public AppointmentDto save(CreateAppointmentDto dto){
        Patient patient = patientService.convertToPatient(patientService.getPatientById(dto.getPatientId()));
        Doctor doctor = doctorService.convertToDoctor(doctorService.getDoctorById(dto.getDoctorId()));

        Appointment appointment = new Appointment();
        appointment.setAppointment_date(dto.getAppointment_date());
        appointment.setAppointment_type(dto.getAppointment_type());
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        repository.save(appointment);
        return appointmentDto.convertToDTO(appointment);
    }

    public AppointmentDto update(Long id, CreateAppointmentDto dto){
        Patient patient = patientService.convertToPatient(patientService.getPatientById(dto.getPatientId()));
        Doctor doctor = doctorService.convertToDoctor(doctorService.getDoctorById(dto.getDoctorId()));
        Appointment appointment = findById(id);
        appointment.setAppointment_date(dto.getAppointment_date());
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        repository.save(appointment);
        return appointmentDto.convertToDTO(appointment);
    }

    public void delete(Long id){
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StudentLecture not found with id: " + id));
        repository.delete(appointment);
    }

}
