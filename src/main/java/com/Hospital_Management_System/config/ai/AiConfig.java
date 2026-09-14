//package com.Hospital_Management_System.config.ai;
//
//import com.Hospital_Management_System.controller.*;
//import com.Hospital_Management_System.service.AdmissionService;
//import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AiConfig {
//
//    @Bean
//    public ChatClient chatClient(ChatClient.Builder builder, AppointmentController appointmentController,
//                                 BedController bedController,
//                                 BillController billController,
//                                 DepartmentController departmentController,
//                                 DoctorController doctorController,
//                                 InsuranceController insuranceController,
//                                 MedicineController medicineController,
//                                 PatientController patientController,
//                                 PaymentController paymentController,
//                                 PresriptionIteamController presriptionIteamController,
//                                 RoomController roomController
//                                 ) {
//
//
//        return builder
//                .defaultSystem(
//                        "You are a helpful Hospital Management System assistant. " +
//                                "When a user wants to perform an operation, use the appropriate tool. " +
//                                "If required information is missing, ask the user for it before calling the tool."
//                ).defaultTools(
//                        roomController
//                        ,patientController
//                        )
//                .build();
//    }
//
//
//}
