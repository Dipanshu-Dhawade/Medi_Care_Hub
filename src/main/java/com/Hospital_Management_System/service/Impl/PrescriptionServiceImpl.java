package com.Hospital_Management_System.service.Impl;

import com.Hospital_Management_System.dto.request.PrescriptionRequestDto;
import com.Hospital_Management_System.dto.responce.PrescriptionResponseDto;
import com.Hospital_Management_System.model.Doctor;
import com.Hospital_Management_System.model.Patient;
import com.Hospital_Management_System.model.Prescription;
import com.Hospital_Management_System.repository.AppointmentRepository;
import com.Hospital_Management_System.repository.DoctorRepository;
import com.Hospital_Management_System.repository.PatientRepository;
import com.Hospital_Management_System.repository.PrescriptionRepository;
import com.Hospital_Management_System.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final PrescriptionRepository prescriptionRepository;

//    @Override
//    public void addPrescription(Long patient_id, Long doctor_id, JsonNode json) {
//
//    }

    @Override
    public PrescriptionResponseDto createPrescription(Long patient_id, Long doctor_id, JsonNode json) {
        boolean b = appointmentRepository.existsByPatient_IdAndDoctor_Id(patient_id, doctor_id);
        if (!b) throw new RuntimeException("appointment is not done yet by " + patient_id + " " + doctor_id);
        Patient patient = patientRepository.findById(patient_id).
                orElseThrow(() -> new RuntimeException("patient id is not Found " + patient_id));
        Doctor doctor = doctorRepository.findById(doctor_id).
                orElseThrow(() -> new RuntimeException("doctor id is not found " + doctor_id));

        Prescription prescription = new Prescription();
        prescription.setNotes(json.get("notes").asText());
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        Prescription save = prescriptionRepository.save(prescription);

        return new PrescriptionResponseDto(save.getNotes());
    }


    @Override
    public PrescriptionResponseDto getPrescriptionById(Long prescriptionId) {
        Prescription prescription = prescriptionRepository.findById(prescriptionId).
                orElseThrow(() -> new RuntimeException("Prescription is Not Found " + prescriptionId));
        return new PrescriptionResponseDto(prescription.getNotes());
    }

    @Override
    public List<JsonNode> getAllPrescriptions() {

        List<Prescription> prescriptions =
                prescriptionRepository.findAll();

        List<JsonNode> response = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        JSONParser jsonParser = new JSONParser();

        for (Prescription prescription : prescriptions) {

            try {

                // Create JSON Object
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("id", prescription.getId());
                jsonObject.put("visitDate",
                        prescription.getVisit_date().toString());
                jsonObject.put("notes",
                        prescription.getNotes());

                jsonObject.put("patientId",
                        prescription.getPatient().getId());

                jsonObject.put("doctorId",
                        prescription.getDoctor().getId());

                // Convert JSONObject to String
                String jsonString = jsonObject.toJSONString();

                // Parse JSON String
                Object parsed = jsonParser.parse(jsonString);

                // Convert to JsonNode
                JsonNode node =
                        objectMapper.readTree(parsed.toString());

                response.add(node);

            } catch (Exception e) {
                throw new RuntimeException(
                        "Error converting prescription", e);
            }
        }

        return response;
    }

    @Override
    public List<PrescriptionResponseDto> getPrescriptionsByPatient(Long patientId) {
        List<Prescription> prescriptions = prescriptionRepository.findByPatient_Id(patientId);
        if (prescriptions.isEmpty()) throw new RuntimeException("Precreption is empty");

        List<PrescriptionResponseDto> dtos = new ArrayList<>();
        for (var prescription : prescriptions) {
            PrescriptionResponseDto dto = new PrescriptionResponseDto();
            dto.setNotes(prescription.getNotes());
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<PrescriptionResponseDto> getPrescriptionsByDoctor(Long doctorId) {
        List<Prescription> prescriptions = prescriptionRepository.findByDoctor_Id(doctorId);
        if (prescriptions.isEmpty()) throw new RuntimeException("Prescription are Empty");
        List<PrescriptionResponseDto> dtos = new ArrayList<>();
        for (var prescription : prescriptions) {
            PrescriptionResponseDto dto = new PrescriptionResponseDto();
            dto.setNotes(prescription.getNotes());
            dtos.add(dto);
        }
        return dtos;
    }
    @Override
    public PrescriptionResponseDto updatePrescription(
            Long prescriptionId,
            String requestDto) throws ParseException {

        JSONParser parser = new JSONParser();

        JSONObject jsonObject =
                (JSONObject) parser.parse(requestDto);

        Prescription prescription =
                prescriptionRepository.findById(prescriptionId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Prescription not found "
                                                + prescriptionId));

        Long patientId =
                Long.parseLong(
                        jsonObject.get("patientid").toString()
                );

        Patient patient =
                patientRepository.findById(patientId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Patient is Not Found "
                                                + patientId));

        Long doctorId =
                Long.parseLong(
                        jsonObject.get("doctorid").toString()
                );

        Doctor doctor =
                doctorRepository.findById(doctorId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Doctor is Not Found "
                                                + doctorId));

        prescription.setNotes(
                jsonObject.get("Notes").toString()
        );

        prescription.setVisit_date(
                LocalDate.parse(
                        jsonObject.get("visit_date").toString()
                )
        );

        prescription.setPatient(patient);
        prescription.setDoctor(doctor);

        Prescription updated =
                prescriptionRepository.save(prescription);

        PrescriptionResponseDto dto =
                new PrescriptionResponseDto();

        dto.setNotes(updated.getNotes());

        return dto;
    }
    @Override
    public PrescriptionResponseDto patchPrescription(
            Long prescriptionId,
            PrescriptionRequestDto requestDto) {

        Prescription prescription =
                prescriptionRepository.findById(prescriptionId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Prescription not found "
                                                + prescriptionId));

        if (requestDto.getNotes() != null) {
            prescription.setNotes(
                    requestDto.getNotes()
            );
        }

        if (requestDto.getVisit_date() != null) {
            prescription.setVisit_date(
                    requestDto.getVisit_date()
            );
        }

        if (requestDto.getPatient()!=null && requestDto.getPatient().getId() != null) {

            Patient patient =
                    patientRepository.findById(
                            requestDto.getPatient().getId()
                    ).orElseThrow(() ->
                            new RuntimeException(
                                    "Patient not found"));

            prescription.setPatient(patient);
        }

        if (requestDto.getDoctor()!=null &&requestDto.getDoctor().getId() != null) {

            Doctor doctor =
                    doctorRepository.findById(
                            requestDto.getDoctor().getId()
                    ).orElseThrow(() ->
                            new RuntimeException(
                                    "Doctor not found"));

            prescription.setDoctor(doctor);
        }

        Prescription updated =
                prescriptionRepository.save(prescription);

        PrescriptionResponseDto dto =
                new PrescriptionResponseDto();
        dto.setNotes(updated.getNotes());

        return dto;
    }

    @Override
    public void deletePrescription(Long prescriptionId) {

        Prescription prescription =
                prescriptionRepository.findById(prescriptionId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Prescription not found "
                                                + prescriptionId));

        prescription.setDeleted(true);

        prescriptionRepository.save(prescription);
    }

    @Override
    public void  deletePermenantPrescription(Long prescriptionId){
        Prescription prescription =
                prescriptionRepository.findById(prescriptionId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Prescription not found "
                                                + prescriptionId));
        prescriptionRepository.deleteById(prescriptionId);
    }


}
