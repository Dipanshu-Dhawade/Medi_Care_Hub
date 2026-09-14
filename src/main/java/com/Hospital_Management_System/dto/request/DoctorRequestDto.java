package com.Hospital_Management_System.dto.request;

import com.Hospital_Management_System.model.Department;
import com.Hospital_Management_System.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequestDto {
    private String name;
    private User user;
    private String specialization;
    private String phone;
    private String email;
    private int exprience_years;
    private boolean deleted = false;
    private String gender;
    private Set<Department> department = new HashSet<>();
}
