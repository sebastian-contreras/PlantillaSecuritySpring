package com.sebastianContreras.PlantillaSecurity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/greeting")
public class GreetingControllers {
    @GetMapping("/hello")
    public String sayHello(){
        return "Welcome!";
    }
    @GetMapping("/helloprivate")
    public String sayHelloprivate(){
        return "Welcome! user private";
    }
}
