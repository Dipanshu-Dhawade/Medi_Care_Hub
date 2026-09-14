package com.Hospital_Management_System.dto.responce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionIteamResponceDto {

    private Long id;
    private String dosage;
    private String frequency;
    private String duration;
    private String MedicineName;
    private Long prescriptionid;

    private Long medicineid;


}
