package com.Hospital_Management_System.controller;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hospital_Management_System.service.PaymentService;

import lombok.RequiredArgsConstructor;
import tools.jackson.databind.JsonNode;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @Tool (name = "addPayment", description = "This endpoint is used to add a payment for a bill.")
    @PostMapping("/addPayment/{billId}")
    public ResponseEntity<JsonNode> addPayment(
            @PathVariable Long billId,
            @RequestBody JsonNode json) {
        JsonNode response = paymentService.addPaymentService(
                billId,
                json
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}