package com.Hospital_Management_System.service;
import com.Hospital_Management_System.dto.request.DepartmentRequestDto;
import com.Hospital_Management_System.dto.responce.DepartmentResponceDto;

import java.util.List;

public interface DepartmentService {

    // CREATE
    DepartmentResponceDto addDepartment(
            DepartmentRequestDto departmentRequestDto
    );

    // READ ALL
    List<DepartmentResponceDto> getAllDepartments();

    // READ BY ID
    DepartmentResponceDto getDepartmentById(Long id);

    // UPDATE
    DepartmentResponceDto updateDepartment(
            Long id,
            DepartmentRequestDto departmentRequestDto
    );

    // PATCH
    DepartmentResponceDto patchDepartment(
            Long id,
            DepartmentRequestDto departmentRequestDto
    );

    // SOFT DELETE
    void deleteDepartment(Long id);
}
