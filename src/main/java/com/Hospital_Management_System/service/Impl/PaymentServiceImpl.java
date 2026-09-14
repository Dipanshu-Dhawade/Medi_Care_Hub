package com.Hospital_Management_System.service.Impl;

import com.Hospital_Management_System.constant.PaymentModeConstant;
import com.Hospital_Management_System.model.Bill;
import com.Hospital_Management_System.model.Payment;
import com.Hospital_Management_System.repository.BillRepository;
import com.Hospital_Management_System.repository.PaymentRepository;
import com.Hospital_Management_System.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final BillRepository billRepository;
    private final ObjectMapper objectMapper;

    @Override
    public JsonNode addPaymentService(Long billId, JsonNode json) {

        Bill bill = billRepository.findById(billId)
                .orElseThrow(() ->
                        new RuntimeException("Bill id is not found"));

        Payment payment = new Payment();

        payment.setPaymentDate(LocalDate.now());

        payment.setAmount(
                json.get("amount").asLong()
        );

        payment.setPaymentMode(
                json.get("paymentmode").asText()
        );

        payment.setTransactionId(
                json.get("transactionId").asText()
        );

        payment.setStatus(
                json.get("status").asText()
        );
        payment.setBill(bill);
        Payment savedPayment = paymentRepository.save(payment);

        return objectMapper.valueToTree(savedPayment);
    }
}