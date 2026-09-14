package com.Hospital_Management_System.service.Impl;

import com.Hospital_Management_System.constant.BillStatusConstant;
import com.Hospital_Management_System.model.Bill;
import com.Hospital_Management_System.model.Patient;
import com.Hospital_Management_System.model.User;
import com.Hospital_Management_System.repository.BillRepository;
import com.Hospital_Management_System.repository.PatientRepository;
import com.Hospital_Management_System.repository.UserRepository;
import com.Hospital_Management_System.service.BillService;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BillServiceImpl  implements BillService {

    private  final BillRepository billRepository;
    private final ObjectMapper objectMapper;
    private  final PatientRepository patientRepository;
    @Override
    public JsonNode addBillService(Long patient_id,JsonNode json) {
        Patient patient = patientRepository.findById(patient_id)
                .orElseThrow(() -> new RuntimeException("your are not patient"));
        Bill bill1 = new Bill();
        bill1.setDiscount(json.get("Discount").asDecimal());
        bill1.setTotalAmount(json.get("TotalAmount").asDecimal());
        bill1.setNetAmount(json.get("NetAmount").asDecimal());
        bill1.setPatient(patient);
        bill1.setStatus(
                json.get("status").asText()
        );
        Bill save = billRepository.save(bill1);
        return  objectMapper.valueToTree(save);
    }
}
