package com.Hospital_Management_System.controller;

import com.Hospital_Management_System.dto.request.MedicineRequestDto;
import com.Hospital_Management_System.dto.responce.MedicineResponceDto;
import com.Hospital_Management_System.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicine")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;


    // =========================
    // ADD SINGLE MEDICINE
    // =========================
    @PostMapping("/add")
    public ResponseEntity<String> addMedicine(
            @RequestBody MedicineRequestDto medicineRequestDto) {

        boolean status =
                medicineService.addMedicine(medicineRequestDto);

        if (status) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Medicine added successfully");
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Medicine not added");
    }


    // =========================
    // ADD MULTIPLE MEDICINES
    // =========================
    @PostMapping("/add-multiple")
    public ResponseEntity<String> addMultipleMedicine(
            @RequestBody List<MedicineRequestDto> medicines) {

        boolean status =
                medicineService.addMultipleMedicine(medicines);

        if (status) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Medicines added successfully");
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Medicines not added");
    }


    // =========================
    // GET MEDICINE BY ID
    // =========================
    @GetMapping("/{id}")
    public ResponseEntity<MedicineResponceDto> getMedicineById(
            @PathVariable Long id) {

        MedicineResponceDto medicine =
                medicineService.getMedicineById(id);

        return ResponseEntity.ok(medicine);
    }


    // =========================
    // GET ALL MEDICINES
    // =========================
    @GetMapping("/all")
    public ResponseEntity<List<MedicineResponceDto>> getAllMedicine() {

        List<MedicineResponceDto> medicines =
                medicineService.getAllMedicine();

        return ResponseEntity.ok(medicines);
    }


    // =========================
    // COMPLETE UPDATE
    // =========================
    @PutMapping("/{id}")
    public ResponseEntity<MedicineResponceDto> updateMedicine(
            @PathVariable Long id,
            @RequestBody MedicineRequestDto medicineRequestDto) {

        MedicineResponceDto updatedMedicine =
                medicineService.updateMedicine(
                        id,
                        medicineRequestDto
                );

        return ResponseEntity.ok(updatedMedicine);
    }


    // =========================
    // DELETE MEDICINE
    // =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMedicine(
            @PathVariable Long id) {

        boolean status =
                medicineService.deleteMedicine(id);

        if (status) {
            return ResponseEntity.ok(
                    "Medicine deleted successfully"
            );
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Medicine not deleted");
    }
}