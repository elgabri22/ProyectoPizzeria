package com.example.ejemplo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/auth/login"; // Redirige a la página de login
    }

    @GetMapping("/auth/home/{username}")
    public String home(@PathVariable String username, Model model) {
        model.addAttribute("correo",username);
        return "home"; // Redirige a la página de login
    }
}
