//    package com.Hospital_Management_System.security;
//
//
//    import org.springframework.beans.factory.annotation.Autowired;
//    import org.springframework.context.annotation.Bean;
//    import org.springframework.context.annotation.Configuration;
//    import org.springframework.security.config.Customizer;
//    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//    import org.springframework.security.config.http.SessionCreationPolicy;
//    import org.springframework.security.core.userdetails.User;
//    import org.springframework.security.core.userdetails.UserDetails;
//    import org.springframework.security.core.userdetails.UserDetailsService;
//    import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//    import org.springframework.security.web.SecurityFilterChain;
//    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//    @Configuration
//    public class webSecurity {
//
//        @Autowired
//        Config config;
//
//
//    //    @Bean
//    //    public SecurityFilterChain securityFiler1(HttpSecurity httpSecurity){
//    //         httpSecurity.
//    //                formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.loginPage());
//    //
//    //        return httpSecurity.build();
//    //    }
//
//
//        // csrf
//    // Another website tricking your browser into sending a request to your
//    //  application while you are already logged in.
//        @Bean
//        public SecurityFilterChain securityFilterChain(
//                HttpSecurity http,
//                JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
//
//            return http
//                    .csrf(csrf -> csrf.disable())
//
//                    .sessionManagement(session ->
//                            session.sessionCreationPolicy(
//                                    SessionCreationPolicy.STATELESS
//                            )
//                    )
//
//                    .authorizeHttpRequests(request -> request
//                            .requestMatchers("/public/**").permitAll()
//                            .requestMatchers("/api/auth/**").permitAll()
//                            .requestMatchers("/api/patients/**").permitAll()
//                            .requestMatchers("/signin/**").permitAll()
//                                .requestMatchers("/Doctor/**").hasRole("Doctor")
//                            .anyRequest().authenticated()
//                    )
//
//                    .addFilterBefore(
//                            jwtAuthenticationFilter,
//                            UsernamePasswordAuthenticationFilter.class
//                    )
//
//                    .build();
//        }
//
//
//
//       // @Bean
//    //    public UserDetailsService userDetailsService(){
//    //
//    //        UserDetails userDetails1 = User
//    //                .withUsername("Amit").
//    //                password(config.getPasswordEncoder().encode("amit@123")).
//    //                roles("Patient")
//    //                .build();
//    //        UserDetails userDetails2 = User
//    //                .withUsername("Rakesh").
//    //                password(config.getPasswordEncoder().encode("rakesh@123")).
//    //                roles("Doctor")
//    //                .build();
//    //        return new InMemoryUserDetailsManager(userDetails1,userDetails2);
//    //
//    //    }
//
//    }