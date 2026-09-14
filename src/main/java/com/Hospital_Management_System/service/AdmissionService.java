package com.Hospital_Management_System.service;

import com.Hospital_Management_System.model.Admission;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface AdmissionService {

    public Admission addAdmission(
            @PathVariable Long prescription_id,
            @PathVariable Long patient_id,
            @PathVariable Long doctor_id,
            @RequestBody JSONObject admission
    ) throws ParseException;

}
