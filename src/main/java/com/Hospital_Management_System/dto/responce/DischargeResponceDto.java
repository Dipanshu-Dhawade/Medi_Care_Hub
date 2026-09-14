package com.Hospital_Management_System.dto.responce;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DischargeResponceDto {

    private  int id;
    private LocalDateTime nextFollowUp;

}
