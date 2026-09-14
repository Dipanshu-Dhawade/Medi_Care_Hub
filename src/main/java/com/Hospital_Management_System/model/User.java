package com.Hospital_Management_System.model;

import com.Hospital_Management_System.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long  id;

    @Column(unique = true ,nullable = false)
    private  String username;

    @Column(nullable = false)
    private  String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleTB role;

    @Column(nullable = false)
    private boolean deleted =false;

    @OneToOne(mappedBy ="user",cascade = CascadeType.ALL)
    private Patient patient_user;

    @OneToOne(mappedBy ="user")
    private  Doctor doctor_user;

    }

