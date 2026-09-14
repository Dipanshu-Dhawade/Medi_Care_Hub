package com.Hospital_Management_System.dto.request;

import com.Hospital_Management_System.model.Doctor;
import com.Hospital_Management_System.model.Patient;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionRequestDto {

    private String notes;

    private LocalDate visit_date;

    private Patient patient;

    private Doctor doctor;
}
