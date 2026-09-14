package com.Hospital_Management_System.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private  User user;
    private String name;
    private  String specialization;
    private  String phone;
    private  String  email;
    private  String  gender;
    private  int exprience_years;

    @Column(nullable = false)
    private boolean deleted =false;

    @ManyToMany
    @JoinTable(name ="doctor_department",
            joinColumns =@JoinColumn(name = "doctor_id"),
            inverseJoinColumns =@JoinColumn(name = "department_id")
    )
    @JsonIgnore
    private Set<Department> department= new HashSet<>();

    @OneToMany(mappedBy ="doctor")
    @JsonIgnore
    private List<Appointment> appointmentList= new ArrayList<>();
}
