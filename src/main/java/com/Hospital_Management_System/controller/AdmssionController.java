package com.Hospital_Management_System.controller;

import com.Hospital_Management_System.service.Impl.EmailService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Hospital_Management_System.model.Admission;
import com.Hospital_Management_System.service.AdmissionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class AdmssionController {

    private  final AdmissionService admissionService;
    private  final  EmailService emailService;

   @PostMapping("/addAdmission/{prescription_id}/{patient_id}/{doctor_id}")
    public ResponseEntity<Admission> addAdmission(
            @PathVariable Long prescription_id,
            @PathVariable Long patient_id,
            @PathVariable Long doctor_id,
            @RequestBody String admission) throws ParseException {

        JSONParser parser = new JSONParser();
        JSONObject parse = (JSONObject) parser.parse(admission);
        return ResponseEntity.ok(
                admissionService.addAdmission(
                        prescription_id,
                        patient_id,
                        doctor_id,
                        parse
                )
        );
    }

    @GetMapping("/send")
    public ResponseEntity<String> sendUserEmail() {

        emailService.sendEmail(
                "dipanshudhawade88@gmail.com",
                "Appointment Confirmation",
                "Your appointment has been successfully scheduled."
        );

        return ResponseEntity.ok("Email sent successfully");
    }

}
