package com.example.ejemplo.servicios;

import com.example.ejemplo.entidades.Usuario;
import com.example.ejemplo.repositorios.UsuarioRepository;
import com.example.ejemplo.servicios.security.UserInfoDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.ejemplo.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    public String addUser(Usuario usuario){
        System.out.println(usuario.getPassword());
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        System.out.println(usuario.getPassword());
        usuarioRepository.save(usuario);
        return "Usuario registrado correctamente";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> userDetail = usuarioRepository.findByEmail(username); // Assuming 'email' is used as username

        // Converting UserInfo to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

    }
}
