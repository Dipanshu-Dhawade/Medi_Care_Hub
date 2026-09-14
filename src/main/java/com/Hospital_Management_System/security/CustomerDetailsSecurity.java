//package com.Hospital_Management_System.security;
//
//import com.Hospital_Management_System.model.User;
//import com.Hospital_Management_System.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class CustomerDetailsSecurity  implements UserDetailsService {
//
//     private  final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User byUsername = userRepository.findByUsername(username);
//        if(byUsername==null) throw  new RuntimeException("you are not singup");
//        return (UserDetails) byUsername;
//    }
//}
