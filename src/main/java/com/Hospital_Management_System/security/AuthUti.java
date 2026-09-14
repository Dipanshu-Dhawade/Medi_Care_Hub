//package com.Hospital_Management_System.security;
//
//import com.Hospital_Management_System.enums.Role;
//import com.Hospital_Management_System.model.User;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.SecretKey;
//import java.nio.charset.StandardCharsets;
//import java.util.Date;
//
//@Service
//public class AuthUti {
//
//    @Value("${screateKey}")
//    private String secreateKey;
//
//    public SecretKey getSecreateKey(){
//        return  Keys.hmacShaKeyFor(secreateKey.getBytes(StandardCharsets.UTF_8));
//    }
//    @Value("${expiration}")
//    private String expiration;
//
//     public String  createJwt(User user){
//             return Jwts.builder()
//                     .subject(user.getUsername())
//                     .claim("userID",user.getId())
//                     .claim("userRole",user.getRole().getRole().name())
//                     .claim("userEmail",user.getPatient_user().getEmail())
//                     .issuedAt(new Date())
//                     .expiration(new Date(System.currentTimeMillis()+Long.parseLong(expiration)))
//                     .signWith(getSecreateKey())
//                     .compact();
//     }
//
//    public String extractUsername(String token) {
//
//
//
//        return Jwts.parser()
//                .verifyWith(getSecreateKey())
//                .build()
//                .parseSignedClaims(token)
//                .getPayload()
//                .getSubject();
//    }
//    public boolean isTokenValid(
//            String token,
//            UserDetails userDetails) {
//        String username = extractUsername(token);
//        return username.equals(userDetails.getUsername());
//    }
//}
