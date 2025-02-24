package com.example.ejemplo.servicios;

import com.example.ejemplo.entidades.Pedido;
import com.example.ejemplo.entidades.Pizza;
import com.example.ejemplo.repositorios.PedidoRepository;
import com.example.ejemplo.repositorios.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

    public Pizza findPizzaById(String id){
        Pizza pizza =null;
        Optional<Pizza> pizzaOptional= this.pizzaRepository.findById(id);
        if (pizzaOptional.isPresent()){
            pizza=pizzaOptional.get();
        }
        return pizza;
    }

    public List<Pizza> findAll(){
        return this.pizzaRepository.findAll();
    }

    public void insertaPizza(Pizza pizza){
        this.pizzaRepository.save(pizza);
    }

    public void updatePizza(Pizza pizza){
        this.pizzaRepository.save(pizza);
    }

    public void deletePizza(Pizza pizza){
        this.pizzaRepository.deleteById(pizza.get_id().toString());
    }
}
