package com.Hospital_Management_System;

import com.Hospital_Management_System.enums.RoomType;
import com.Hospital_Management_System.model.Appointment;
import com.Hospital_Management_System.model.Medicine;
import com.Hospital_Management_System.repository.AppointmentRepository;
import com.Hospital_Management_System.repository.PrescriptionIteamRepository;
import com.Hospital_Management_System.repository.RoomRepository;
import com.Hospital_Management_System.service.Impl.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest

class AppointmentTest {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PrescriptionIteamRepository  prescriptionIteamRepository;

    @Autowired
    private EmailService emailService;

    @Test
    void findPatientAppointments() {
        emailService.sendEmail(
                "dipanshudhawade88@gmail.com",
                "Appointment Confirmation",
                "Your appointment has been successfully scheduled."
        );
        }
}