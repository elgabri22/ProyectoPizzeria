package com.example.ejemplo.apis;

import com.example.ejemplo.entidades.Pedido;
import com.example.ejemplo.entidades.Pizza;
import com.example.ejemplo.entidades.Usuario;
import com.example.ejemplo.servicios.PedidoService;
import com.example.ejemplo.servicios.PizzaService;
import com.example.ejemplo.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apipedido")
public class ApiPedidoController {
    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/getPedidos")
    public ResponseEntity<List<Pedido>> getPedidos(){
        List<Pedido>pedidos=this.pedidoService.findAll();
        if (pedidos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/getPedidos/{email}")
    public ResponseEntity<Pedido> getPedido(@PathVariable String id){
        Pedido pedido=this.pedidoService.findPedidoById(id);
        if (pedido==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedido);
    }

    @GetMapping("/getPedidosCliente/{correo}")
    public ResponseEntity<?> getPedidosUsuario(@PathVariable String correo) {
        Usuario usuario = this.usuarioService.findUsuarioByEmail(correo);

        // Validar si el usuario existe
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }

        List<Pedido> pedidos = this.pedidoService.findPedidoByCliente(correo);

        // Validar si el usuario no tiene pedidos
        if (pedidos == null || pedidos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(pedidos);
    }


    @PostMapping("/insertaPizza")
    public ResponseEntity<String> insertaPedido(@RequestBody Pedido pedido){
        this.pedidoService.insertaPedido(pedido);
        return ResponseEntity.ok("Pizza insertada correctamente");
    }

    @PostMapping("/updatePizza")
    public ResponseEntity<String> updatePedido(@RequestBody Pedido pedido){
        this.pedidoService.insertaPedido(pedido);
        return ResponseEntity.ok("Pizza actualizada correctamente");
    }

    @PostMapping("/deletePizza/{id}")
    public ResponseEntity<String> borraPedido(@RequestBody Pedido pedido){
        this.pedidoService.deletePedido(pedido);
        return ResponseEntity.ok("Pizza insertada correctamente");
    }
}
