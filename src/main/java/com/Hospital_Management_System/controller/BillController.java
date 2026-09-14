package com.Hospital_Management_System.controller;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hospital_Management_System.repository.BillRepository;
import com.Hospital_Management_System.service.BillService;

import lombok.RequiredArgsConstructor;
import tools.jackson.databind.JsonNode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bill")
public class BillController {

    private final BillService billService;
    private final BillRepository billRepository;

    @Tool(name = "addBill", description = "This endpoint is used to add a bill for a patient.")
    @PostMapping("/addBill/{patient_id}")
    public ResponseEntity<JsonNode> addBill(
            @PathVariable("patient_id") Long patient_id,
            @RequestBody JsonNode json) {

        JsonNode response = billService.addBillService(patient_id,json);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

}