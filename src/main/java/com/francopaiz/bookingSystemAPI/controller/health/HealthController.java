package com.francopaiz.bookingSystemAPI.controller.health;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/api/v1/health")
    public String checkAPI(){

         return "<h1>The API is working ok!</h1>";
    }
}
