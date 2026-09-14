package com.Hospital_Management_System.dto.request;


import com.Hospital_Management_System.enums.BloodGroup;
import com.Hospital_Management_System.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequestDto {
        private String name;
        private String gender;
        private LocalDate birthDate;
        private String email;
        private String phoneNumber;
        private BloodGroup bloodGroup;
        private Address address;

}
