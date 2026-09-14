package com.Hospital_Management_System.service;

import com.Hospital_Management_System.dto.request.RolePermissionRequest;
import com.Hospital_Management_System.model.Permission;
import com.Hospital_Management_System.model.RoleTB;
import com.Hospital_Management_System.repository.PermissionRepository;
import com.Hospital_Management_System.repository.RoleTBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private RoleTBRepository roleTBRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    public RoleTB assignPermissions(RolePermissionRequest request) {

        RoleTB role = roleTBRepository.findByRole(request.getRole());

        if (role == null) {
            throw new RuntimeException("Role not found");
        }
        Set<Permission> permissions = new HashSet<>();

        for (var permissionName : request.getPermissions()) {
            Permission permission = permissionRepository.findByPermission(permissionName);

            if (permission == null) {
                throw new RuntimeException("Permission not found: " + permissionName);
            }

            permissions.add(permission);
        }

        role.setPermissions(permissions);

        return roleTBRepository.save(role);
    }
}