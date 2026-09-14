package com.Hospital_Management_System.dto.responce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceResponceDto {
    private Long id;

    private String   policyNumber;

    private  String  provider;
}
