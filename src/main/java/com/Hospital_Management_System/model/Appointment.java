package com.Hospital_Management_System.model;


import com.Hospital_Management_System.enums.StatusEnums;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @CreatedDate
    private LocalDate appointment_date;

    @CreationTimestamp
    private LocalTime appointment_time;

    @Enumerated(EnumType.STRING)
    private StatusEnums status;

    private String reason;

    @Column(nullable = false)
    private boolean deleted =false;

    @ManyToOne
    @JoinColumn(name ="patient_id")
    @JsonIgnore
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonIgnore
    private  Doctor doctor;

}
