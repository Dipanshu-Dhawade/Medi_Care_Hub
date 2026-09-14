package com.Hospital_Management_System.dto.responce;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentResponseDto {
    private  Long id;
    private String reason;

    @CreatedDate
    private LocalDate appointment_date;

    @CreationTimestamp
    private LocalTime appointment_time;

    public AppointmentResponseDto(Long id, String reason) {
        this.id = id;
        this.reason = reason;
    }
}
