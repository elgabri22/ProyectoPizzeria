package com.example.ejemplo.servicios;

import com.example.ejemplo.entidades.Pedido;
import com.example.ejemplo.repositorios.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido findPedidoById(String id){
        Pedido pedido=null;
        Optional<Pedido> pedidoOptional= this.pedidoRepository.findById(id);
        if (pedidoOptional.isPresent()){
            pedido=pedidoOptional.get();
        }
        return pedido;
    }

    public List<Pedido> findPedidoByCliente(String cliente){
        return this.pedidoRepository.findByCliente(cliente);
    }

    public List<Pedido> findAll(){
        return this.pedidoRepository.findAll();
    }

    public void insertaPedido(Pedido pedido){
        this.pedidoRepository.save(pedido);
    }

    public void updatePedido(Pedido pedido){
        this.pedidoRepository.save(pedido);
    }

    public void deletePedido(Pedido pedido){
        this.pedidoRepository.deleteById(pedido.get_id());
    }
}
