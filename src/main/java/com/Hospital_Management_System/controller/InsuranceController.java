package com.Hospital_Management_System.controller;

import com.Hospital_Management_System.dto.request.InsuranceRequestDto;
import com.Hospital_Management_System.dto.responce.InsuranceResponceDto;
import com.Hospital_Management_System.model.Insurance;
import com.Hospital_Management_System.service.InsuranceSerice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/insurance")
@RequiredArgsConstructor
public class InsuranceController {

    private final InsuranceSerice insuranceSerice;


    // =========================
    // ASSIGN INSURANCE
    // =========================
    @PostMapping("/assign/{patientId}")
    public ResponseEntity<Insurance> assignInsuranceToPatient(
            @PathVariable Long patientId,
            @RequestBody Insurance insurance) {

        Insurance savedInsurance =
                insuranceSerice.assingInsuranceToPatient(
                        insurance,
                        patientId
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedInsurance);
    }


    // =========================
    // GET INSURANCE BY ID
    // =========================
    @GetMapping("/{insuranceId}")
    public ResponseEntity<InsuranceResponceDto> getInsuranceById(
            @PathVariable Long insuranceId) {

        InsuranceResponceDto insurance =
                insuranceSerice.getInsuranceById(insuranceId);

        return ResponseEntity.ok(insurance);
    }


    // =========================
    // GET ALL INSURANCE
    // =========================
    @GetMapping("/all")
    public ResponseEntity<List<InsuranceResponceDto>> getAllInsurance() {

        List<InsuranceResponceDto> insurance =
                insuranceSerice.getAllInsurance(null);

        return ResponseEntity.ok(insurance);
    }


    // =========================
    // UPDATE INSURANCE
    // =========================
    @PutMapping("/{insuranceId}")
    public ResponseEntity<InsuranceResponceDto> updateInsurance(
            @PathVariable Long insuranceId,
            @RequestBody InsuranceRequestDto insuranceRequestDto) {

        InsuranceResponceDto updatedInsurance =
                insuranceSerice.updateInsurance(
                        insuranceId,
                        insuranceRequestDto
                );

        return ResponseEntity.ok(updatedInsurance);
    }


    // =========================
    // UNASSIGN INSURANCE
    // =========================
    @DeleteMapping("/unassign/{patientId}")
    public ResponseEntity<String> deassignInsuranceFromPatient(
            @PathVariable Long patientId) {

        Boolean status =
                insuranceSerice.deassingInsuranceToPatient(patientId);

        if (status) {
            return ResponseEntity.ok(
                    "Insurance deassigned from patient successfully"
            );
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Insurance deassignment failed");
    }
}