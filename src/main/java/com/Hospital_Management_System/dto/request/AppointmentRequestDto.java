package com.Hospital_Management_System.dto.request;

import com.Hospital_Management_System.enums.StatusEnums;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentRequestDto {

    private StatusEnums status;

    private String reason;


    private LocalDate appointment_date;

    private LocalTime appointment_time;

}