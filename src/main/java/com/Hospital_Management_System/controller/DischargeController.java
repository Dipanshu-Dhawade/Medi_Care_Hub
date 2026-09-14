package com.Hospital_Management_System.controller;

import com.Hospital_Management_System.dto.request.DischargeRequestDto;
import com.Hospital_Management_System.dto.responce.DischargeResponceDto;
import com.Hospital_Management_System.service.DischargeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discharge")
@RequiredArgsConstructor
public class DischargeController {

    private final DischargeService dischargeService;


    // =====================================================
    // CREATE - Discharge particular patient
    // =====================================================
    @PostMapping("/admission/{admissionId}")
    public ResponseEntity<DischargeResponceDto> dischargePerticularPerson(
            @PathVariable Long admissionId,
             @RequestBody DischargeRequestDto dischargeRequestDto) {

        DischargeResponceDto response =
                dischargeService.dischargePerticularPerson(
                        admissionId,
                        dischargeRequestDto
                );

        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }


    // =====================================================
    // READ - Get all discharges
    // =====================================================
    @GetMapping
    public ResponseEntity<List<DischargeResponceDto>> getAllDischarges() {

        List<DischargeResponceDto> response =
                dischargeService.getAllDischarges();

        return ResponseEntity.ok(response);
    }


    // =====================================================
    // READ - Get discharge by ID
    // =====================================================
    @GetMapping("/{id}")
    public ResponseEntity<DischargeResponceDto> getDischargeById(
            @PathVariable Long id) {

        DischargeResponceDto response =
                dischargeService.getDischargeById(id);

        return ResponseEntity.ok(response);
    }


    // =====================================================
    // READ - Get discharge by Admission ID
    // =====================================================
    @GetMapping("/admission/{admissionId}")
    public ResponseEntity<DischargeResponceDto> getDischargeByAdmissionId(
            @PathVariable Long admissionId) {

        DischargeResponceDto response =
                dischargeService.getDischargeByAdmissionId(admissionId);

        return ResponseEntity.ok(response);
    }


    // =====================================================
    // UPDATE - Complete update
    // =====================================================
    @PutMapping("/{id}")
    public ResponseEntity<DischargeResponceDto> updateDischarge(
            @PathVariable Long id,
            @RequestBody DischargeRequestDto dischargeRequestDto) {

        DischargeResponceDto response =
                dischargeService.updateDischarge(
                        id,
                        dischargeRequestDto
                );

        return ResponseEntity.ok(response);
    }


    // =====================================================
    // PATCH - Partial update
    // =====================================================
    @PatchMapping("/{id}")
    public ResponseEntity<DischargeResponceDto> patchDischarge(
            @PathVariable Long id,
            @RequestBody DischargeRequestDto dischargeRequestDto) {

        DischargeResponceDto response =
                dischargeService.patchDischarge(
                        id,
                        dischargeRequestDto
                );

        return ResponseEntity.ok(response);
    }


    // =====================================================
    // DELETE
    // =====================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDischarge(
            @PathVariable Long id) {

        dischargeService.deleteDischarge(id);

        return ResponseEntity.ok(
                "Discharge deleted successfully"
        );
    }
}
