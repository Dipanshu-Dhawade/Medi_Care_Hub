package com.Hospital_Management_System.dto.responce;

import com.Hospital_Management_System.model.Department;
import com.Hospital_Management_System.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponceDto {
    private Long id;
    private String name;
    private  String specialization;

}
