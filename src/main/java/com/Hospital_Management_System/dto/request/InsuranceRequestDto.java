package com.Hospital_Management_System.dto.request;

import com.Hospital_Management_System.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceRequestDto {
    private String   policyNumber;
    private String provider;

    private LocalDate validUntil;

    private boolean deleted = false;

    private LocalDateTime createdAt;

    private Long  patientid;
}
