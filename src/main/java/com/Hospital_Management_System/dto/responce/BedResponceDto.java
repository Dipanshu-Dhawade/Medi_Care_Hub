package com.Hospital_Management_System.dto.responce;

import com.Hospital_Management_System.enums.BedStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BedResponceDto {
    private String bedNumber;

    private BedStatus status;

}
