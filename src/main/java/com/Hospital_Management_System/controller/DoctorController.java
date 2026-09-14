package com.Hospital_Management_System.controller;

import com.Hospital_Management_System.dto.request.DoctorRequestDto;
import com.Hospital_Management_System.dto.responce.DoctorResponceDto;
import com.Hospital_Management_System.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;


    // CREATE DOCTOR
    @PostMapping("/user/{userId}/department/{departId}")
    public ResponseEntity<DoctorResponceDto> saveDoctor(
            @PathVariable Long userId,
            @PathVariable Long departId,
            @RequestBody DoctorRequestDto doctorRequestDto) {

        DoctorResponceDto response =
                doctorService.saveDoctor(userId, departId, doctorRequestDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    // GET DOCTOR BY USER ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<DoctorResponceDto> getDoctorByUserId(
            @PathVariable Long userId) {
        DoctorResponceDto response = doctorService.getDoctorById(userId);
        return ResponseEntity.ok(response);
    }

    // GET ALL DOCTORS
    @GetMapping
    public ResponseEntity<List<DoctorResponceDto>> getAllDoctors() {

        List<DoctorResponceDto> response =
                doctorService.getAllDoctors();

        return ResponseEntity.ok(response);
    }


    // UPDATE DOCTOR
    @PutMapping("/{doctorId}")
    public ResponseEntity<DoctorResponceDto> updateDoctor(
            @PathVariable Long doctorId,
            @RequestBody DoctorRequestDto doctorRequestDto) {

        DoctorResponceDto response =
                doctorService.updateDoctor(doctorId, doctorRequestDto);

        return ResponseEntity.ok(response);
    }


    // PATCH DOCTOR
    @PatchMapping("/{doctorId}")
    public ResponseEntity<DoctorResponceDto> patchDoctor(
            @PathVariable Long doctorId,
            @RequestBody DoctorRequestDto doctorRequestDto) {

        DoctorResponceDto response =
                doctorService.patchDoctor(doctorId, doctorRequestDto);

        return ResponseEntity.ok(response);
    }


    // DELETE DOCTOR
    @DeleteMapping("/{doctorId}")
    public ResponseEntity<Void> deleteDoctor(
            @PathVariable Long doctorId) {

        doctorService.deleteDoctor(doctorId);

        return ResponseEntity.noContent().build();
    }

    // GET DOCTORS BY DEPARTMENT
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<DoctorResponceDto>> getByDepartmentId(
            @PathVariable Long departmentId) {

        List<DoctorResponceDto> response =
                doctorService.getByDepartmentId(departmentId);

        return ResponseEntity.ok(response);
    }


    // SEARCH DOCTORS
    @GetMapping("/search")
    public ResponseEntity<List<DoctorResponceDto>> searchDoctors(
            @RequestParam String keyword) {

        List<DoctorResponceDto> response =
                doctorService.searchDoctors(keyword);

        return ResponseEntity.ok(response);
    }
}