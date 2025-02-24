package com.example.ejemplo.repositorios;

import com.example.ejemplo.entidades.Pedido;
import com.example.ejemplo.entidades.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends MongoRepository<Pedido,String> {
    List<Pedido> findByCliente(String cliente);
}
