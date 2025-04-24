package com.sau.hospitalapp.Dto;

import com.sau.hospitalapp.Model.Doctor;
import lombok.Data;
@Data
public class DoctorDto {
    public Long id;
    public String name;
    public String department;
    public int patientCount;

    public static DoctorDto convertToDTO(Doctor doctor){
        DoctorDto dto = new DoctorDto();
        dto.setId(doctor.getId());
        dto.setName(doctor.getName());
        dto.setDepartment(doctor.getDepartment());
        dto.setPatientCount(doctor.getPatientDoctors().size());
        return dto;
    }
}
