package com.carrental.controller;

import com.carrental.dao.model.Client;
import com.carrental.dao.model.Order;
import com.carrental.dao.model.Role;
import com.carrental.dao.model.OrderDto;
import com.carrental.service.ClientService;
import com.carrental.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderController {
    private final OrderService orderService;
    private final ClientService clientService;

    public OrderController(OrderService orderService, ClientService clientService) {
        this.orderService = orderService;
        this.clientService = clientService;
    }

    @GetMapping("/order/{id}")
    public String orderAdd(@PathVariable("id") Integer id, Model model, OrderDto order) {
        model.addAttribute("order", new OrderDto(id));

        return "order";
    }

    @PostMapping("/order")
    public String addOrder(@ModelAttribute OrderDto order) {
        orderService.save(order);
        return "redirect:/cars";
    }

    @GetMapping("/myOrder")
    public String showMyOrder(Model model, Principal principal) {
        List<Order> myOrders = new ArrayList<>();
        Client clientByUsername = clientService.findClientByUsername(principal.getName());
        Role role = clientByUsername.getRole();
        if (role == Role.ROLE_ADMIN) {
            myOrders = orderService.findAllOrder();
        } else if (role==Role.ROLE_USER) {
            myOrders = orderService.findAllOrder().stream().filter(order -> order.getIdClient().getId()==clientByUsername.getId()).collect(Collectors.toList());
        }
        model.addAttribute("orders", myOrders);
        return "myOrder";

    }

    @GetMapping("/orderDelete/{id}")
    public String deleteOrder(@PathVariable("id") Integer id){
        orderService.deleteById(id);
        return "redirect:/myOrder";
    }


}
