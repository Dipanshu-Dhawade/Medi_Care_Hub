package com.Hospital_Management_System.repository;

import com.Hospital_Management_System.dto.request.RolePermissionRequest;
import com.Hospital_Management_System.enums.PermissionType;
import com.Hospital_Management_System.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByPermission(PermissionType permissionType);
}