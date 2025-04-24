package com.sau.hospitalapp.Dto;

import lombok.Data;

import java.util.Date;
@Data
public class CreateAppointmentDto {
    private Long id;
    private Date appointment_date;
    private String appointment_type;
    private Long patientId;
    private Long doctorId ;
}
