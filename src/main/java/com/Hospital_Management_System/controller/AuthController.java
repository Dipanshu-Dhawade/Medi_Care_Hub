//package com.Hospital_Management_System.controller;
//
//import com.Hospital_Management_System.dto.request.LoginRequestDto;
//import com.Hospital_Management_System.dto.request.RolePermissionRequest;
//import com.Hospital_Management_System.dto.request.UserSignUpRequestDto;
//import com.Hospital_Management_System.dto.responce.LoginResponceDto;
//import com.Hospital_Management_System.dto.responce.UserLoginResponceDto;
//import com.Hospital_Management_System.model.RoleTB;
//
//import com.Hospital_Management_System.service.RoleService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/public")
//public class AuthController {
//
//    private  final AuthService authService;
//
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponceDto> userLgin(@RequestBody LoginRequestDto loginRequestDto){
//        return  ResponseEntity.ok(authService.loginUser(loginRequestDto));
//    }
//
//    @PostMapping("/signup")
//    public ResponseEntity<UserLoginResponceDto> signup(@RequestBody UserSignUpRequestDto loginRequestDto){
//        return  ResponseEntity.ok(authService.signUser(loginRequestDto));
//    }
//
//    @Autowired
//    private RoleService roleService;
//
//    @PostMapping("/assign-permissions")
//    public ResponseEntity<RoleTB> assignPermissions(
//            @RequestBody RolePermissionRequest request) {
//        RoleTB role = roleService.assignPermissions(request);
//
//        return ResponseEntity.ok(role);
//    }
//
//
//}
//
