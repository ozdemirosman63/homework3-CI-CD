package com.sau.hospitalapp.Dto;

import com.sau.hospitalapp.Model.Appointment;
import lombok.Data;
import org.springframework.context.support.ApplicationObjectSupport;

import java.util.Date;
@Data
public class AppointmentDto {
    private Long id;
    private Date appointment_date;
    private String department;
    private String appointment_type;
    private String patientName;
    private String doctorName;
        public AppointmentDto convertToDTO(Appointment appointment) {
            AppointmentDto dto = new AppointmentDto();
        dto.setId(appointment.getId());
        dto.setAppointment_date(appointment.getAppointment_date());
        dto.setAppointment_type(appointment.getAppointment_type());
        dto.setPatientName(appointment.getPatient().getName()+ " " + appointment.getPatient().getSurname());
        dto.setDoctorName(appointment.getDoctor().getName());
        dto.setDepartment(appointment.getDoctor().getDepartment());

        return dto;
    }
}
