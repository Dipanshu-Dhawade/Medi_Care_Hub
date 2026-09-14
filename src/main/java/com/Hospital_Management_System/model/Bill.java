package com.Hospital_Management_System.model;

import com.Hospital_Management_System.constant.BillStatusConstant;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDate billDate;

    private BigDecimal totalAmount;

    private BigDecimal discount;

    private BigDecimal netAmount;

    @Column(nullable = false)
    private boolean deleted =false;


    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @OneToMany(
            mappedBy = "bill",
            cascade = CascadeType.REMOVE
    )
    private List<Payment> payments = new ArrayList<>();
}