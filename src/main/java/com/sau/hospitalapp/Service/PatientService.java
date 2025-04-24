package com.sau.hospitalapp.Service;

import com.sau.hospitalapp.Dto.PatientDto;
import com.sau.hospitalapp.Exception.ResourceNotFoundException;
import com.sau.hospitalapp.Model.Patient;
import com.sau.hospitalapp.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    PatientDto patientDto = new PatientDto();

    public List<PatientDto> getAll(){
        List<Patient> patients = patientRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return patients.stream()
                .map(s -> patientDto.convertToDTO(s))
                .collect(Collectors.toList());
    }

    public PatientDto getPatientById(Long pationId){
        Patient patient = patientRepository.findById(pationId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + pationId));
        return patientDto.convertToDTO(patient);
    }

    public Patient save(Patient patient){
        return patientRepository.save(patient);
    }

    public PatientDto update(Long patientId, Patient patient){
        Patient patientToUpdate = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + patientId));
        patientToUpdate.setName(patient.getName());
        patientToUpdate.setSurname(patient.getSurname());
        patientToUpdate.setAddress(patient.getAddress());
        patientRepository.save(patientToUpdate);
        return PatientDto.convertToDTO(patientToUpdate);
    }

    public void delete(Long id){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
        patientRepository.delete(patient);
    }

    public Patient convertToPatient(PatientDto patientDto) {
        return new Patient(patientDto.getId(), patientDto.getName(), patientDto.getSurname(),patientDto.getAddress());
    }
}
