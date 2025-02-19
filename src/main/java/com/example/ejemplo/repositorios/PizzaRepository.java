package com.example.ejemplo.repositorios;

import com.example.ejemplo.entidades.Pizza;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PizzaRepository extends MongoRepository<Pizza,String> {
}
