package com.example.ejemplo.apis;

import com.example.ejemplo.entidades.Usuario;
import com.example.ejemplo.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apiusuario")
public class ApiUsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @GetMapping("/getRol/{email}")
    public ResponseEntity<String> getRol(@PathVariable String email) {
        Usuario usuario=this.usuarioService.findUsuarioByEmail(email);
        if (usuario==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario.getRol());
    }
}
