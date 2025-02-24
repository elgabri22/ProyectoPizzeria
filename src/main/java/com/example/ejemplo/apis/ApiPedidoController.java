package com.example.ejemplo.apis;

import com.example.ejemplo.entidades.Pedido;
import com.example.ejemplo.entidades.Pizza;
import com.example.ejemplo.servicios.PedidoService;
import com.example.ejemplo.servicios.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apipedido")
public class ApiPedidoController {
    @Autowired
    private PedidoService pedidoService;


    @GetMapping("/getPedidos")
    public ResponseEntity<List<Pedido>> getPedidos(){
        List<Pedido>pedidos=this.pedidoService.findAll();
        if (pedidos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/getPedidos/{id}")
    public ResponseEntity<Pedido> getPedido(@PathVariable String id){
        Pedido pedido=this.pedidoService.findPedidoById(id);
        if (pedido==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedido);
    }

    @GetMapping("/getPedidos/{id_usuario}")
    public ResponseEntity<List<Pedido>> getPedidosUsuario(@PathVariable String id_usuario){
        List<Pedido> pedidos=this.pedidoService.findPedidoByCliente(id_usuario);
        if (pedidos.size()==0){
            return ResponseEntity.noContent().build();
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
