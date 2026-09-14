package com.Hospital_Management_System.repository;

import com.Hospital_Management_System.enums.Role;
import com.Hospital_Management_System.model.RoleTB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleTBRepository extends JpaRepository<RoleTB, Long> {

    @Query("SELECT r FROM RoleTB r WHERE r.role = :role")
    RoleTB findByRole(@Param("role") Role role);
}