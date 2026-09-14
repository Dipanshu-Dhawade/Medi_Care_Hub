package com.Hospital_Management_System.controller;

import com.Hospital_Management_System.dto.request.DepartmentRequestDto;
import com.Hospital_Management_System.dto.responce.DepartmentResponceDto;
import com.Hospital_Management_System.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    // ========================= CREATE =========================
    @PostMapping
    public ResponseEntity<DepartmentResponceDto> addDepartment(
            @RequestBody DepartmentRequestDto departmentRequestDto) {

        DepartmentResponceDto response =
                departmentService.addDepartment(departmentRequestDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // ========================= READ ALL =========================
    @GetMapping
    public ResponseEntity<List<DepartmentResponceDto>> getAllDepartments() {

        List<DepartmentResponceDto> response =
                departmentService.getAllDepartments();

        return ResponseEntity.ok(response);
    }

    // ========================= READ BY ID =========================
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponceDto> getDepartmentById(
            @PathVariable Long id) {

        DepartmentResponceDto response =
                departmentService.getDepartmentById(id);

        return ResponseEntity.ok(response);
    }

    // ========================= UPDATE =========================
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponceDto> updateDepartment(
            @PathVariable Long id,
            @RequestBody DepartmentRequestDto departmentRequestDto) {

        DepartmentResponceDto response =
                departmentService.updateDepartment(id, departmentRequestDto);

        return ResponseEntity.ok(response);
    }

    // ========================= PATCH =========================
    @PatchMapping("/{id}")
    public ResponseEntity<DepartmentResponceDto> patchDepartment(
            @PathVariable Long id,
            @RequestBody DepartmentRequestDto departmentRequestDto) {

        DepartmentResponceDto response =
                departmentService.patchDepartment(id, departmentRequestDto);

        return ResponseEntity.ok(response);
    }

    // ========================= DELETE =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(
            @PathVariable Long id) {

        departmentService.deleteDepartment(id);

        return ResponseEntity.ok("Department deleted successfully.");
    }
}