package com.Hospital_Management_System.service;

import com.Hospital_Management_System.dto.request.DoctorRequestDto;
import com.Hospital_Management_System.dto.responce.DoctorResponceDto;
import com.Hospital_Management_System.model.Doctor;
import com.Hospital_Management_System.model.Patient;

import java.util.List;

public interface DoctorService {

    // Create
    DoctorResponceDto saveDoctor(Long userId, Long departmentId, DoctorRequestDto doctorRequestDto);

    // Read
    DoctorResponceDto getDoctorById(Long doctorId);

    List<DoctorResponceDto> getAllDoctors();

    // Update
    DoctorResponceDto updateDoctor(
            Long doctorId,
            DoctorRequestDto doctorRequestDto
    );

    DoctorResponceDto patchDoctor(Long doctorId , DoctorRequestDto doctorRequestDto);

    // Delete
    void deleteDoctor(Long doctorId);

    List<DoctorResponceDto> getByDepartmentId(Long departmentId);

    // Optional
    List<DoctorResponceDto> searchDoctors(String keyword);
}