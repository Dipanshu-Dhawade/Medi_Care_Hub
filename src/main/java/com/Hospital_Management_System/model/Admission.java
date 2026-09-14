package com.Hospital_Management_System.model;

import com.Hospital_Management_System.enums.AdmissionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Admission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDateTime admission;

    private String reson;

    @Enumerated(EnumType.STRING)
    private AdmissionStatus statusEnums;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "bed_id")
    private Bed bed;

    @OneToOne(mappedBy = "admission")
    private Discharge discharge;
}