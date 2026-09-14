package com.Hospital_Management_System.dto.request;

import com.Hospital_Management_System.enums.PermissionType;
import com.Hospital_Management_System.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class RolePermissionRequest {

    private Role role;
    private Set<PermissionType> permissions;

}