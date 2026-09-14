package com.Hospital_Management_System.controller;

import com.Hospital_Management_System.dto.request.PrescriptionRequestDto;
import com.Hospital_Management_System.dto.responce.PrescriptionResponseDto;
import com.Hospital_Management_System.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.JsonNode;

import java.util.List;

@RestController
@RequestMapping("/api/prescription")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService prescriptionService;


    // CREATE PRESCRIPTION
    @PostMapping("/patient/{patientId}/doctor/{doctorId}")
    public ResponseEntity<PrescriptionResponseDto> createPrescription(
            @PathVariable Long patientId,
            @PathVariable Long doctorId,
            @RequestBody JsonNode json) {

        PrescriptionResponseDto response =
                prescriptionService.createPrescription(
                        patientId,
                        doctorId,
                        json
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    // GET PRESCRIPTION BY ID
    @GetMapping("/{prescriptionId}")
    public ResponseEntity<PrescriptionResponseDto> getPrescriptionById(
            @PathVariable Long prescriptionId) {

        PrescriptionResponseDto response =
                prescriptionService.getPrescriptionById(
                        prescriptionId
                );

        return ResponseEntity.ok(response);
    }


    // GET ALL PRESCRIPTIONS
    @GetMapping
    public ResponseEntity<List<JsonNode>> getAllPrescriptions() {

        List<JsonNode> response =
                prescriptionService.getAllPrescriptions();

        return ResponseEntity.ok(response);
    }


    // GET PRESCRIPTIONS BY PATIENT
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<PrescriptionResponseDto>>
    getPrescriptionsByPatient(
            @PathVariable Long patientId) {

        List<PrescriptionResponseDto> response =
                prescriptionService.getPrescriptionsByPatient(
                        patientId
                );

        return ResponseEntity.ok(response);
    }


    // GET PRESCRIPTIONS BY DOCTOR
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<PrescriptionResponseDto>>
    getPrescriptionsByDoctor(
            @PathVariable Long doctorId) {

        List<PrescriptionResponseDto> response =
                prescriptionService.getPrescriptionsByDoctor(
                        doctorId
                );

        return ResponseEntity.ok(response);
    }


    // COMPLETE UPDATE
    @PutMapping("/{prescriptionId}")
    public ResponseEntity<PrescriptionResponseDto> updatePrescription(
            @PathVariable Long prescriptionId,
            @RequestBody String requestDto) throws ParseException {

        PrescriptionResponseDto response =
                prescriptionService.updatePrescription(
                        prescriptionId,
                         requestDto
                );

        return ResponseEntity.ok(response);
    }


    // PARTIAL UPDATE
    @PatchMapping("/{prescriptionId}")
    public ResponseEntity<PrescriptionResponseDto> patchPrescription(
            @PathVariable Long prescriptionId,
            @RequestBody PrescriptionRequestDto requestDto) {

        PrescriptionResponseDto response =
                prescriptionService.patchPrescription(
                        prescriptionId,
                        requestDto
                );

        return ResponseEntity.ok(response);
    }


    // DELETE PRESCRIPTION
    @DeleteMapping("/{prescriptionId}")
    public ResponseEntity<String> deletePrescription(
            @PathVariable Long prescriptionId) {

        prescriptionService.deletePresscription(
                prescriptionId
        );

        return ResponseEntity.ok(
                "Prescription deleted successfully"
        );
    }
}