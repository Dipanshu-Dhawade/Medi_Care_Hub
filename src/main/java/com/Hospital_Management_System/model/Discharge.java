package com.Hospital_Management_System.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Discharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "admission_id", unique = true)
    private Admission admission;

    private LocalDateTime dischargeDate;

    private String summary;

    private LocalDate nextFollowUp;
}