package com.Hospital_Management_System.service;

import com.Hospital_Management_System.model.Bill;
import tools.jackson.databind.JsonNode;

public interface BillService {

    public JsonNode   addBillService(Long patient_id,JsonNode bill);
}
