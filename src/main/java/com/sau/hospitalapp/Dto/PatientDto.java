package com.sau.hospitalapp.Dto;

import com.sau.hospitalapp.Model.Patient;
import lombok.Data;

@Data
public class PatientDto {
    private Long id;
    private String name;
    private String surname;
    private String address;
    public static PatientDto convertToDTO(Patient patient) {
        PatientDto dto = new PatientDto();
        dto.setId(patient.getId());
        dto.setName(patient.getName());
        dto.setSurname(patient.getSurname());
        dto.setAddress(patient.getAddress());
        return dto;
    }
}
