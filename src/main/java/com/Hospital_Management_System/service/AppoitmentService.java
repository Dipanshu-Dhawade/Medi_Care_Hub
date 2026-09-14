package com.Hospital_Management_System.service;

import com.Hospital_Management_System.dto.request.AppointmentRequestDto;
import com.Hospital_Management_System.dto.responce.AppointmentResponseDto;
import com.Hospital_Management_System.model.Appointment;
import org.json.simple.parser.ParseException;

import java.util.List;

public interface AppoitmentService {

   // CREATE
   AppointmentResponseDto createAppointment(
           Long patientId,
           Long doctorId,
           String appointmentData
   )throws ParseException;


   // READ - by appointment ID
   AppointmentResponseDto getAppointmentById(
           Long appointmentId
   );

   // READ - all appointments
   List<AppointmentResponseDto> getAllAppointments();

   // READ - patient's appointments
   List<AppointmentResponseDto> getAppointmentsByPatientId(
           Long patientId
   );

   // READ - doctor's appointments
   List<AppointmentResponseDto> getAppointmentsByDoctorId(
           Long doctorId
   );

   // UPDATE - complete update
   AppointmentResponseDto updateAppointment(
           Long appointmentId,
           AppointmentRequestDto requestDto
   );

   // PATCH - partial update
   AppointmentResponseDto patchAppointment(
           Long appointmentId,
           AppointmentRequestDto requestDto
   );

   // DELETE
   void deleteAppointment(
           Long appointmentId
   );


//   public List<Appointment> findAllPateintAndDeletedFalse(Long id );
//   public List<Appointment> findAppointWithPateintAndDoctor();
//   public List<Appointment> findAllAppointmentDoctorMoreThanSixExp();
//   public List<Appointment> findAppointmentWithPateintOnly(long id);
//   public List<Object[]> findAllMothtlyAppointmentCount();
}
