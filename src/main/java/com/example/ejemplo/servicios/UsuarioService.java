package com.example.ejemplo.servicios;

import com.example.ejemplo.entidades.Pedido;
import com.example.ejemplo.entidades.Usuario;
import com.example.ejemplo.repositorios.PedidoRepository;
import com.example.ejemplo.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findUsuarioById(String id){
        Usuario usuario=null;
        Optional<Usuario> pedidoOptional= this.usuarioRepository.findById(id);
        if (pedidoOptional.isPresent()){
            usuario=pedidoOptional.get();
        }
        return usuario;
    }

    public List<Usuario> findAll(){
        return this.usuarioRepository.findAll();
    }

    public void insertaUsuario(Usuario usuario){
        this.usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Usuario usuario){
        this.usuarioRepository.deleteById(usuario.getId());
    }
}
