package com.example.ejemplo.controller;

import com.example.ejemplo.entidades.Pedido;
import com.example.ejemplo.repositorios.PedidoRepository;
import com.example.ejemplo.servicios.PedidoService;
import com.example.ejemplo.servicios.PizzaService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class PizzaController {
    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/pizzas/{username}")
    public String pizzas(Model model, @PathVariable String username){
        model.addAttribute("pizzas",this.pizzaService.findAll());
        return "pizzas";
    }
    @GetMapping("/pedidos/{username}")
    public String pedidos(Model model, @PathVariable String username){
        model.addAttribute("username",username);
        return "pedidos";
    }
    @GetMapping("/crearPedidos/{username}")
    public String creaPedidos(@ModelAttribute Pedido pedido, BindingResult bindingResult, Model model,@PathVariable String username){
        model.addAttribute("pizzas",this.pizzaService.findAll());
        model.addAttribute("username",username);
        return "addPedido";
    }

    @GetMapping("/editaPedidos/{username}/{id_pedido}")
    public String creaPedidos(@ModelAttribute Pedido pedido, BindingResult bindingResult, Model model,@PathVariable String username,@PathVariable String id_pedido){
        model.addAttribute("pizzas",this.pizzaService.findAll());
        model.addAttribute("pedido", this.pedidoService.findPedidoById(id_pedido));
        model.addAttribute("username",username);
        model.addAttribute("id",id_pedido);
        return "editPedido";
    }

    @PostMapping("/addPedidos/{username}")
    public String addPedidos(@ModelAttribute Pedido pedido, BindingResult bindingResult, Model model,@PathVariable String username){
        pedido.setCliente(username);
        pedido.setFecha_pedido(new Date());
        this.pedidoService.insertaPedido(pedido);
        return "redirect:/pedidos/"+username;
    }
    @PostMapping("/editPedidos/{username}/{id}")
    public String editPedidos(@ModelAttribute("pedido") Pedido pedido, BindingResult result, Model model,@PathVariable String username,@PathVariable String id){
        if (result.hasErrors()) {
            return "editPedido";
        }else {
            pedido.setCliente(username);
            pedido.set_id(new ObjectId(id));
            this.pedidoService.updatePedido(pedido);
        }
        return "redirect:/pedidos/"+username;
    }
    @GetMapping("/deletePedido/{id}/{username}")
    public String deletePedido(@PathVariable String id,@PathVariable String username){
        this.pedidoService.deletePedidoById(id);
        return "redirect:/pedidos/"+username;
    }
}
