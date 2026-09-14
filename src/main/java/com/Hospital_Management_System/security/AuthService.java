//package com.Hospital_Management_System.security;
//
//
//import com.Hospital_Management_System.dto.request.LoginRequestDto;
//
//import com.Hospital_Management_System.dto.request.UserSignUpRequestDto;
//import com.Hospital_Management_System.dto.responce.LoginResponceDto;
//import com.Hospital_Management_System.dto.responce.SignLoginResponceDto;
//import com.Hospital_Management_System.dto.responce.UserLoginResponceDto;
//import com.Hospital_Management_System.enums.Role;
//import com.Hospital_Management_System.model.Patient;
//import com.Hospital_Management_System.model.RoleTB;
//import com.Hospital_Management_System.model.User;
//import com.Hospital_Management_System.repository.PatientRepository;
//import com.Hospital_Management_System.repository.RoleTBRepository;
//import com.Hospital_Management_System.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AuthService {
//
//    private final AuthenticationManager authenticationManager;
//    private final AuthUti authUti;
//    private final UserRepository userRepository;
//    private final RoleTBRepository roleTBRepository;
//    private  final PatientRepository patientRepository;
//    private final Config config;
//
//    public LoginResponceDto loginUser(LoginRequestDto loginRequestDto) {
//        Authentication authenticate = authenticationManager.
//                authenticate(new UsernamePasswordAuthenticationToken
//                        (loginRequestDto.getUsername(), loginRequestDto.getPassword()));
//        User user = (User) authenticate.getPrincipal();
//        String jwt = authUti.createJwt(user);
//        return new LoginResponceDto(user.getId(), jwt);
//    }
//
//    public UserLoginResponceDto signUser(UserSignUpRequestDto userSignUpRequestDto) {
//        User byUsername = userRepository.findByUsername(userSignUpRequestDto.getUsername());
//        if (byUsername != null) throw new RuntimeException("you are already sign In");
//
//        RoleTB byRole = roleTBRepository.findByRole(userSignUpRequestDto.getRole());
//        if (byRole == null) throw new RuntimeException("role is not in  db");
//
//        Patient patient = Patient
//                .builder()
//                .email(userSignUpRequestDto.getEmail())
//                .build();
//
//        User user = User.builder()
//                .role(byRole)
//                .patient_user(patient)
//                .username(userSignUpRequestDto.getUsername())
//                .password(config.getPasswordEncoder().encode(userSignUpRequestDto.getPassword()))
//                .build();
//        patient.setUser(user);
//        User save = userRepository.save(user);
//        patientRepository.save(patient);
//        return new UserLoginResponceDto(save.getId(), save.getUsername());
//    }
//
//
//}
