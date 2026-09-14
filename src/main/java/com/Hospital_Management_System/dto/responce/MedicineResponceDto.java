package com.Hospital_Management_System.dto.responce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineResponceDto {
    private String name;
    private Long  price;
    private String categoary;
    private Long  unit=0L;
}
