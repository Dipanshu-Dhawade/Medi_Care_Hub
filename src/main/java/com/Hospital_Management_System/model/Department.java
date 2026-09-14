package com.Hospital_Management_System.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String name;

    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(nullable = false)
    private boolean deleted =false;

    //bidireactional  mapping
    @ManyToMany(mappedBy="department")
    @JsonIgnore
    private Set<Doctor> doctors = new HashSet<>();

}
