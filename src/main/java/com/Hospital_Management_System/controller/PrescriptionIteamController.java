package com.Hospital_Management_System.controller;

import com.Hospital_Management_System.dto.request.PrescriptionIteamRequestDto;
import com.Hospital_Management_System.dto.responce.PrescriptionIteamResponceDto;
import com.Hospital_Management_System.model.Medicine;
import com.Hospital_Management_System.model.PrescriptionIteam;
import com.Hospital_Management_System.service.PrescriptionIteamService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prescription-item")
@RequiredArgsConstructor
public class PrescriptionIteamController {

    private final PrescriptionIteamService prescriptionIteamService;


    // ==========================================
    // CREATE - ADD MULTIPLE ITEMS
    // ==========================================
    @PostMapping("/add/{prescriptionId}")
    public ResponseEntity<List<PrescriptionIteam>> add(
            @PathVariable Long prescriptionId,
            @RequestBody JSONObject jsonObject) {

        List<PrescriptionIteam> items =
                prescriptionIteamService.add(
                        prescriptionId,
                        jsonObject
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(items);
    }


    // ==========================================
    // READ - GET ALL
    // ==========================================
    @GetMapping("/all")
    public ResponseEntity<List<PrescriptionIteamResponceDto>> getAll() {

        List<PrescriptionIteamResponceDto> items =
                prescriptionIteamService.getAll();

        return ResponseEntity.ok(items);
    }


    // ==========================================
    // READ - GET BY ID
    // ==========================================
    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionIteamResponceDto> getById(
            @PathVariable Long id) {

        PrescriptionIteamResponceDto item =
                prescriptionIteamService.getById(id);

        return ResponseEntity.ok(item);
    }


    // ==========================================
    // READ - GET MEDICINES BY PRESCRIPTION ID
    // ==========================================
    @GetMapping("/prescription/{prescriptionId}/medicines")
    public ResponseEntity<List<Medicine>>
    getMedicinesByPrescriptionId(
            @PathVariable Long prescriptionId) {

        List<Medicine> medicines =
                prescriptionIteamService
                        .getALLMedicineByPrecriptionId(
                                prescriptionId
                        );

        return ResponseEntity.ok(medicines);
    }


    // ==========================================
    // COMPLETE UPDATE
    // ==========================================
    @PutMapping("/{id}")
    public ResponseEntity<PrescriptionIteamResponceDto> update(
            @PathVariable Long id,
            @RequestBody PrescriptionIteamRequestDto dto) {

        PrescriptionIteamResponceDto updatedItem =
                prescriptionIteamService.update(
                        id,
                        dto
                );

        return ResponseEntity.ok(updatedItem);
    }


    // ==========================================
    // PARTIAL UPDATE
    // ==========================================
    @PatchMapping("/{id}")
    public ResponseEntity<PrescriptionIteamResponceDto> partialUpdate(
            @PathVariable Long id,
            @RequestBody PrescriptionIteamRequestDto dto) {

        PrescriptionIteamResponceDto updatedItem =
                prescriptionIteamService.partialUpdate(
                        id,
                        dto
                );

        return ResponseEntity.ok(updatedItem);
    }


    // ==========================================
    // DELETE
    // ==========================================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id) {

        prescriptionIteamService.delete(id);

        return ResponseEntity.ok(
                "Prescription item deleted successfully"
        );
    }
}