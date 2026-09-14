package com.Hospital_Management_System.controller;

import com.Hospital_Management_System.dto.request.PatientRequestDto;
import com.Hospital_Management_System.dto.responce.PatientResponceDto;
import com.Hospital_Management_System.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;


    // CREATE PATIENT
    @PostMapping("/{userId}")
    public ResponseEntity<PatientResponceDto> savePatient(
            @PathVariable Long userId,
            @RequestBody PatientRequestDto patientRequestDto) {
        PatientResponceDto response = patientService.savePatient(userId, patientRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    // GET PATIENT BY ID
    @GetMapping("/{patientId}")
    public ResponseEntity<PatientResponceDto> getPatientById(
            @PathVariable Long patientId) {
        PatientResponceDto response = patientService.getPatientById(patientId);
        return ResponseEntity.ok(response);
    }


    // GET ALL PATIENTS
    @GetMapping
    public ResponseEntity<List<PatientResponceDto>> getAllPatients() {
        List<PatientResponceDto> response = patientService.getAllPatients();
        return ResponseEntity.ok(response);
    }


    // GET PATIENT BY EMAIL
    @GetMapping("/email/{email}")
    public ResponseEntity<PatientResponceDto> getPatientByEmail(@PathVariable String email) {
        PatientResponceDto response = patientService.getPatientByEmail(email);
        return ResponseEntity.ok(response);
    }


    // GET PATIENT BY PHONE NUMBER
    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<PatientResponceDto> getPatientByPhoneNumber(@PathVariable String phoneNumber) {
        PatientResponceDto response = patientService.getPatientByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(response);
    }


    // FULL UPDATE
    @PutMapping("/{patientId}")
    public ResponseEntity<PatientResponceDto> updatePatient(
            @PathVariable Long patientId,
            @RequestBody PatientRequestDto patientRequestDto) {
        PatientResponceDto response = patientService.updatePatient(patientId, patientRequestDto);
        return ResponseEntity.ok(response);
    }


    // PARTIAL UPDATE
    @PatchMapping("/{patientId}")
    public ResponseEntity<PatientResponceDto> patchPatient(
            @PathVariable Long patientId,
            @RequestBody PatientRequestDto patientRequestDto) {
        PatientResponceDto response = patientService.patchPatient(patientId, patientRequestDto);
        return ResponseEntity.ok(response);
    }


    // HARD DELETE
    @DeleteMapping("/{patientId}")
    public ResponseEntity<String> removePatientById(
            @PathVariable Long patientId) {
        patientService.removePatientById(patientId);
        return ResponseEntity.ok("Patient deleted successfully");
    }


    // SOFT DELETE
    @PatchMapping("/{patientId}/soft-delete")
    public ResponseEntity<String> softDeletePatient(
            @PathVariable Long patientId) {
        patientService.softDeletePatient(patientId);
        return ResponseEntity.ok("Patient soft deleted successfully");
    }
}