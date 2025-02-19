package com.example.ejemplo.apis;

import com.example.ejemplo.entidades.Pizza;
import com.example.ejemplo.servicios.PizzaService;
import com.example.ejemplo.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apipizza")
public class ApiPizzaController {
    @Autowired
    private PizzaService pizzaService;


    @GetMapping("/getPizzas")
    public ResponseEntity<List<Pizza>> getPizzas(){
        List<Pizza>pizzas=this.pizzaService.findAll();
        if (pizzas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pizzas);
    }

    @GetMapping("/getPizza/{id}")
    public ResponseEntity<Pizza> getPizzas(@PathVariable String id){
        Pizza pizza=this.pizzaService.findPizzaById(id);
        if (pizza==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pizza);
    }

    @PostMapping("/insertaPizza")
    public ResponseEntity<String> insertaPizza(@RequestBody Pizza pizza){
        this.pizzaService.insertaPizza(pizza);
        return ResponseEntity.ok("Pizza insertada correctamente");
    }

    @PostMapping("/deletePizza/{id}")
    public ResponseEntity<String> borraPizza(@RequestBody Pizza pizza){
        this.pizzaService.deletePizza(pizza);
        return ResponseEntity.ok("Pizza insertada correctamente");
    }
}
