package com.example.ejemplo.controller;

import com.example.ejemplo.servicios.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PizzaController {
    @Autowired
    private PizzaService pizzaService;
    @GetMapping("/pizzas/{username}")
    public String pizzas(Model model, @PathVariable String username){
        model.addAttribute("pizzas",this.pizzaService.findAll());
        return "pizzas";
    }
    @GetMapping("/pedidos/{username}")
    public String pedidos(Model model, @PathVariable String username){
        return "pedidos";
    }
}
