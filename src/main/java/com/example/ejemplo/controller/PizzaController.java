package com.example.ejemplo.controller;

import com.example.ejemplo.entidades.Pedido;
import com.example.ejemplo.entidades.Pizza;
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
import java.util.Optional;

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
        pedido.setEstado("Pendiente");
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
            pedido.setEstado("Pendiente");
            this.pedidoService.updatePedido(pedido);
        }
        return "redirect:/pedidos/"+username;
    }
    @GetMapping("/deletePedido/{id}/{username}")
    public String deletePedido(@PathVariable String id,@PathVariable String username){
        this.pedidoService.deletePedidoById(id);
        return "redirect:/pedidos/"+username;
    }

    @GetMapping("/deletePizza/{id}/{username}")
    public String deletePizza(@PathVariable String id,@PathVariable String username){
        this.pizzaService.deletePizza(id);
        return "redirect:/pizzas/"+username;
    }

    @GetMapping("/editaPizza/{username}/{id_pizza}")
    public String creaPedidos(@ModelAttribute Pizza pizza, BindingResult bindingResult, Model model, @PathVariable String username, @PathVariable String id_pizza){
        model.addAttribute("username",username);
        model.addAttribute("pizza",this.pizzaService.findPizzaById(id_pizza));
        return "editPizza";
    }

    @GetMapping("/crearPizza/{username}")
    public String creaPizza(@ModelAttribute Pizza pizza, BindingResult bindingResult, Model model,@PathVariable String username){
        model.addAttribute("username",username);
        return "addPizza";
    }

    @PostMapping("/addPizza/{username}")
    public String addPizza(@ModelAttribute Pizza pizza, BindingResult bindingResult, Model model,@PathVariable String username){
        this.pizzaService.insertaPizza(pizza);
        return "redirect:/pizzas/"+username;
    }

    @PostMapping("/editPizza/{username}/{id}")
    public String editPizza(
            @ModelAttribute("pizza") Pizza pizza,
            BindingResult result,
            Model model,
            @PathVariable String username,
            @PathVariable String id) {

        if (result.hasErrors()) {
            model.addAttribute("username", username);
            model.addAttribute("pizza", pizza);
            return "editPizza"; // Devuelve el formulario con los errores
        }

        Pizza existingPizza = pizzaService.findPizzaById(id); // Buscar la pizza en la BD
        if (existingPizza!=null) {
            pizza.set_id(new ObjectId(id)); // Asegurar que el ID es correcto
            pizzaService.updatePizza(pizza);
        } else {
            model.addAttribute("error", "Pizza no encontrada.");
            return "editPizza"; // Redirige al formulario si la pizza no existe
        }

        return "redirect:/pizzas/" + username; // Redirige al listado de pizzas del usuario
    }

}
