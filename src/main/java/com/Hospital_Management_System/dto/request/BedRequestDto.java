package com.Hospital_Management_System.dto.request;

import com.Hospital_Management_System.enums.BedStatus;
import com.Hospital_Management_System.model.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BedRequestDto {

    private String bedNumber;

    private BedStatus status;

}
