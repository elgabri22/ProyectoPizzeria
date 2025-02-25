package com.example.ejemplo.repositorios;

import com.example.ejemplo.entidades.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario,String> {
    Optional<Usuario> findByemail(String email);
}
