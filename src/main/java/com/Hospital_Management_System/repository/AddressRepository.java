package com.Hospital_Management_System.repository;

import com.Hospital_Management_System.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}