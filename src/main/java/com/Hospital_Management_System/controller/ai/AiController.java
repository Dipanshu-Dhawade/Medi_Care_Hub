//package com.Hospital_Management_System.controller.ai;
//
//import com.Hospital_Management_System.service.ai.ChartClientService;
//import jakarta.websocket.server.PathParam;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class AiController {
//    private final ChartClientService chartClientService;
//
//    @GetMapping("/aiAgent")
//    public String chart(@PathParam("query") String query) {
//        return chartClientService.chart(query);
//    }
//
//}
