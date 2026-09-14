package com.Hospital_Management_System.dto.request;

import com.Hospital_Management_System.model.Medicine;
import com.Hospital_Management_System.model.Prescription;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionIteamRequestDto {

    private Long id;
    private String dosage;
    private String frequency;
    private String duration;


    private Long prescriptionid;

    private Long medicineid;
}
