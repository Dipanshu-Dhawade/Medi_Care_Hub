package com.Hospital_Management_System.model;


import com.Hospital_Management_System.enums.BloodGroup;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    private Long id ;

    private String name;

    private String gender;

    private LocalDate birthDate;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "phone_no")
    private String  phoneNumber;

    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    @CreationTimestamp
    private LocalDateTime creatAt;

    @Column(nullable = false)
    private boolean deleted = false;

    //forkey
    @OneToOne(fetch = FetchType.LAZY ,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", unique = true)
    @MapsId
    @JsonIgnore
    private User user;

    @OneToOne(orphanRemoval = true, fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Insurance insurance;

    @ManyToOne()
    @JoinColumn(name = "address")
    @JsonIgnore
    private Address address;

    //mappedBy

    @OneToMany(mappedBy = "patient",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Appointment> patientid;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Bill> bills = new ArrayList<>();

    @OneToMany(mappedBy ="patient",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Prescription> prescriptions;

}
