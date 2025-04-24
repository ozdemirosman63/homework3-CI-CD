package com.sau.hospitalapp.Service;

import com.sau.hospitalapp.Dto.DoctorDto;
import com.sau.hospitalapp.Dto.PatientDto;
import com.sau.hospitalapp.Exception.ResourceNotFoundException;
import com.sau.hospitalapp.Model.Doctor;
import com.sau.hospitalapp.Model.Patient;
import com.sau.hospitalapp.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    DoctorDto doctorDto = new DoctorDto();
    public List<DoctorDto> getAll(){
        List<Doctor> doctors = doctorRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return doctors.stream()
                .map(l -> doctorDto.convertToDTO(l))
                .collect(Collectors.toList());
    }

    public DoctorDto getDoctorById(Long doctorId){
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + doctorId));
        return doctorDto.convertToDTO(doctor);
    }


    public Doctor save(Doctor doctor){
        System.out.println("Doctor ID: " + doctor.getId());
        System.out.println("Doctor Name: " + doctor.getName()); // Eğer null geliyorsa sorun burada
        return doctorRepository.save(doctor);
    }

    public DoctorDto update(Long doctorId, Doctor doctor){
        Doctor doctor_update = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + doctorId));
        doctor_update.setName(doctor.getName());
        doctor_update.setDepartment(doctor.getDepartment());
        doctorRepository.save(doctor_update);

        return doctorDto.convertToDTO(doctor_update);
    }

    public void delete(Long doctorId){
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + doctorId));
        doctorRepository.delete(doctor);
    }
    public Doctor convertToDoctor(DoctorDto doctorDto) {
        return new Doctor(doctorDto.getId(), doctorDto.getName(), doctorDto.getDepartment());
    }
}
