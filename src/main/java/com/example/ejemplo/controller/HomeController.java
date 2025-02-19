package com.example.ejemplo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/auth/login"; // Redirige a la página de login
    }

    @GetMapping("/home")
    public String home() {
        return "home"; // Redirige a la página de login
    }
}
