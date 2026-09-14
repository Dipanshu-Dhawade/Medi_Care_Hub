package com.Hospital_Management_System.service;


import tools.jackson.databind.JsonNode;

public interface PaymentService {
    public JsonNode addPaymentService(Long billId, JsonNode json);
}
