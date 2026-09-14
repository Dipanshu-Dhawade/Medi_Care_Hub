package com.Hospital_Management_System.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DischargeRequestDto {
    private LocalDateTime dischargeDate;

    private String summary;

    private LocalDate nextFollowUp;
}
