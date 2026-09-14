//package com.Hospital_Management_System.service.ai;
//
//import com.Hospital_Management_System.config.ai.AiConfig;
//import com.Hospital_Management_System.controller.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class ChartClientService {
//    private final ChatClient client;
//
//    public String chart(String query) {
//        return client.prompt()
//                .user(query).
//                call()
//                .content();
//    }
//
//}
