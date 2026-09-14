package com.Hospital_Management_System.service.Impl;

import com.Hospital_Management_System.dto.request.AppointmentRequestDto;
import com.Hospital_Management_System.dto.responce.AppointmentResponseDto;
import com.Hospital_Management_System.enums.StatusEnums;
import com.Hospital_Management_System.helper.EmailTemplateHelper;
import com.Hospital_Management_System.helper.SimpleTextFormat;
import com.Hospital_Management_System.model.Appointment;
import com.Hospital_Management_System.model.Doctor;
import com.Hospital_Management_System.model.Patient;
import com.Hospital_Management_System.repository.AppointmentRepository;
import com.Hospital_Management_System.repository.DoctorRepository;
import com.Hospital_Management_System.repository.PatientRepository;
import com.Hospital_Management_System.service.AppoitmentService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AppoitmentServiceImpl implements AppoitmentService {


    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final  EmailTemplateHelper emailTemplateHelper;

    @Override
    public AppointmentResponseDto createAppointment(Long patientId, Long doctorId, String appointmentData) throws ParseException {
        Patient pateint = patientRepository.findById(patientId).get();
             if(pateint==null) throw  new RuntimeException("Patient is not Found"+patientId);
        Doctor doctor_id = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("doctor not found Exception"));

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(appointmentData);
        Appointment appointment = new Appointment();
        appointment.setStatus(StatusEnums.valueOf(jsonObject.get("status").toString()));
        appointment.setReason(jsonObject.get("reason").toString());
        appointment.setDoctor(doctor_id);
        appointment.setPatient(pateint);
        Appointment appointmentsave = appointmentRepository.save(appointment);

        emailTemplateHelper.sendHtmlEmail(
                appointment.getPatient().getEmail(),
                "Appointment Confirmation",
                SimpleTextFormat.createAppointmentEmail(appointment)
        );

        return new AppointmentResponseDto(appointmentsave.getId()
                ,appointmentsave.getReason()
                ,appointmentsave.getAppointment_date()
                ,appointmentsave.getAppointment_time());
    }


    @Override
    public AppointmentResponseDto getAppointmentById(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).
                orElseThrow(() -> new RuntimeException("Appointment is Not Found" + appointmentId));

        return new AppointmentResponseDto(appointment.getId()
                ,appointment.getReason()
                ,appointment.getAppointment_date()
                ,appointment.getAppointment_time());
    }

    @Override
    public List<AppointmentResponseDto> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        List<AppointmentResponseDto> dtos = new ArrayList<>();
        for (var appointment : appointments){
            AppointmentResponseDto dto =new AppointmentResponseDto();
            dto.setReason(appointment.getReason());
            dto.setAppointment_time(appointment.getAppointment_time());
            dto.setAppointment_date(appointment.getAppointment_date());
            dtos.add(dto);
        }
        return  dtos;
    }

    @Override
    public List<AppointmentResponseDto> getAppointmentsByPatientId(Long patientId) {
        List<Appointment> appointments = appointmentRepository.findByPatient_id(patientId);
        List<AppointmentResponseDto> dtos =  new ArrayList<>();

        for (var  appointment :  appointments ){
             AppointmentResponseDto dto= new AppointmentResponseDto();
             dto.setReason(appointment.getReason());
             dtos.add(dto);
        }
      return  dtos;
    }

    @Override
    public List<AppointmentResponseDto> getAppointmentsByDoctorId(Long doctorId) {
        List<Appointment> appointments = appointmentRepository.findBydoctor_id(doctorId);
        if(appointments==null) throw  new RuntimeException("This Doctor is Not Appoiment"+doctorId);

        List<AppointmentResponseDto> dtos = new ArrayList<>();

        for (var appointment: appointments) {
            AppointmentResponseDto dto = new AppointmentResponseDto();
            dto.setId(appointment.getId());
            dto.setAppointment_time(appointment.getAppointment_time());
            dto.setAppointment_date(appointment.getAppointment_date());
            dtos.add(dto);
        }
        return  dtos;
    }

    //complete update
    @Override
    public AppointmentResponseDto updateAppointment(Long appointmentId, AppointmentRequestDto requestDto) {
        Appointment appointment = appointmentRepository.findById(appointmentId).
                orElseThrow(() -> new RuntimeException("Appoitment is NOt Found " + appointmentId));
        appointment.setReason(requestDto.getReason());
        appointment.setStatus(requestDto.getStatus());
        appointment.setAppointment_time(requestDto.getAppointment_time());
        appointment.setAppointment_date(requestDto.getAppointment_date());
        Appointment saveappointment = appointmentRepository.save(appointment);
        return new  AppointmentResponseDto(saveappointment.getId()
                ,saveappointment.getReason()
                ,saveappointment.getAppointment_date()
                ,saveappointment.getAppointment_time());
    }

    @Override
    public AppointmentResponseDto patchAppointment(
            Long appointmentId,
            AppointmentRequestDto requestDto) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() ->
                        new RuntimeException("Appointment is Not Found " + appointmentId));

        if (requestDto.getReason() != null) {
            appointment.setReason(requestDto.getReason());
        }

        if (requestDto.getStatus() != null) {
            appointment.setStatus(requestDto.getStatus());
        }

        if (requestDto.getAppointment_time() != null) {
            appointment.setAppointment_time(requestDto.getAppointment_time());
        }

        if (requestDto.getAppointment_date() != null) {
            appointment.setAppointment_date(requestDto.getAppointment_date());
        }

        Appointment savedAppointment = appointmentRepository.save(appointment);

        return new AppointmentResponseDto(
                savedAppointment.getId(),
                savedAppointment.getReason(),
                savedAppointment.getAppointment_date(),
                savedAppointment.getAppointment_time()
        );
    }

    @Override
    public void deleteAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).
                orElseThrow(() -> new RuntimeException("Appitment is Not Found " + appointmentId));
         appointmentRepository.deleteById(appointmentId);
    }
//
//    public List<Appointment> findAllPateintAndDeletedFalse(Long id ) {
//        List<Appointment> appointments =
//                appointmentRepository
//                        .findByPaitentIdAndDeletedFalse(1L);
//        if (appointments.isEmpty()) throw new RuntimeException("appointment is  empty");
//        return appointments;
//    }
//
//    @GetMapping("/appointmentsWithPatientAndDoctor")
//    public List<Appointment> findAppointWithPateintAndDoctor() {
//        List<Appointment> appointmentsWithPatientAndDoctor = appointmentRepository.findAppointmentsWithPatientAndDoctor();
//        if(appointmentsWithPatientAndDoctor.isEmpty()) throw  new RuntimeException("Empty ");
//        return appointmentsWithPatientAndDoctor;
//    }
//
//    @GetMapping("/appointmentsDoctorExpmorethan6")
//    public List<Appointment> findAllAppointmentDoctorMoreThanSixExp() {
//        List<Appointment> appointmentsDoctormorethan6 = appointmentRepository.
//                findAppointmentsDoctormorethan6();
//        if(appointmentsDoctormorethan6.isEmpty()) throw  new RuntimeException("no one Doctor has more than Six year");
//        return appointmentsDoctormorethan6;
//    }
//
//    @GetMapping("/findAppointmentWithPateintOnly")
//    public List<Appointment> findAppointmentWithPateintOnly(long id) {
//        List<Appointment> appointments = appointmentRepository.findAppointments(id);
////        if (!appointments.isEmpty()) {
////            for(Appointment appointment:appointments){
////                dto.setPartientName(appointment.getPaitent().getName())
////            }
////        }
//        if(appointments.isEmpty()) throw  new RuntimeException("it is  Empty ");
//        return appointments;
//    }
//
//    @GetMapping("/getMonlyAppoitmentCount")
//    public List<Object[]> findAllMothtlyAppointmentCount() {
//        List<Object[]> monthlyAppointmentCount = appointmentRepository.getMonthlyAppointmentCount();
//        if(monthlyAppointmentCount.isEmpty()) throw  new RuntimeException("Appointment is  empty ");
//        return monthlyAppointmentCount;
//    }
//

}
