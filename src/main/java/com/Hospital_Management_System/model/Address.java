    package com.Hospital_Management_System.model;


    import com.Hospital_Management_System.enums.StatusEnums;
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    @Entity
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class Address {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private  Long  id;
        private String street;
        private  String city;
        private String state;
        private String country;
        private  int pincode;

        @Column(nullable = false)
        private boolean deleted =false;
    }
