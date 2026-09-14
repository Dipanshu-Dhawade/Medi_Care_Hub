package com.Hospital_Management_System.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineRequestDto {
    private String name;
    private String categoary;
    private Long  unit=0L;
    private Long price=0L;
}
