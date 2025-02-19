package com.example.ejemplo.repositorios;

import com.example.ejemplo.entidades.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PedidoRepository extends MongoRepository<Pedido,String> {
}
