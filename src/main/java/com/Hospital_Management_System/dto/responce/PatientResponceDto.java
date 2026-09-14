package com.Hospital_Management_System.dto.responce;

import com.Hospital_Management_System.enums.BloodGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponceDto {
    private String name;
    private String gender;
    private BloodGroup bloodGroup;
}