package com.Hospital_Management_System.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.text.PasswordView;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        return  new ModelMapper();
    }
   // @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){
//        return configuration.getAuthenticationManager();
//    }
}
