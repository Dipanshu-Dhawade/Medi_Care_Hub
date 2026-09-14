package com.Hospital_Management_System.controller;

import com.Hospital_Management_System.dto.request.AppointmentRequestDto;
import com.Hospital_Management_System.dto.responce.AppointmentResponseDto;

import com.Hospital_Management_System.service.AppoitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

   private  final AppoitmentService appoitmentService;
    // Create Appointment
    @PostMapping("/patient/{patientId}/doctor/{doctorId}")
    public ResponseEntity<AppointmentResponseDto> createAppointment(
            @PathVariable Long patientId,
            @PathVariable Long doctorId,
            @RequestBody String appointmentData) throws Exception {

        AppointmentResponseDto response =
                appoitmentService.createAppointment(
                        patientId,
                        doctorId,
                        appointmentData
                );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    // Get Appointment By ID
    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentResponseDto> getAppointmentById(
            @PathVariable Long appointmentId) {

        AppointmentResponseDto response =
                appoitmentService.getAppointmentById(appointmentId);

        return ResponseEntity.ok(response);
    }


    // Get All Appointments
    @GetMapping
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointments() {

        List<AppointmentResponseDto> response =
                appoitmentService.getAllAppointments();

        return ResponseEntity.ok(response);
    }


    // Get Appointments By Patient ID
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByPatientId(
            @PathVariable Long patientId) {

        List<AppointmentResponseDto> response =
                appoitmentService.getAppointmentsByPatientId(patientId);

        return ResponseEntity.ok(response);
    }


    // Get Appointments By Doctor ID
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByDoctorId(
            @PathVariable Long doctorId) {

        List<AppointmentResponseDto> response =
                appoitmentService.getAppointmentsByDoctorId(doctorId);

        return ResponseEntity.ok(response);
    }
}