package com.carrental.controller;

import com.carrental.dao.model.Client;
import com.carrental.dao.model.Order;
import com.carrental.service.ClientService;
import com.carrental.service.Order_Service;
import com.carrental.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    private final ClientService clientService;
    private final OrderService orderService;
    private final Order_Service order1Service;

    public AdminController(ClientService clientService, OrderService orderService, Order_Service order1Service) {
        this.clientService = clientService;
        this.orderService = orderService;
        this.order1Service = order1Service;

    }

    @GetMapping("/admin")
    public String allClients(Model model) {
        model.addAttribute("clients", clientService.findAllClient());
        model.addAttribute("orders", orderService.findAllOrder());
        return "adminPage";
    }

    @GetMapping("/clientEdit/{id}")
    public String editClient(@PathVariable("id") Integer id,Model model){
        Client client= clientService.findClientById(id);
        model.addAttribute("client",client);
        return "clientEdit";
    }

    @PostMapping("/clientEdit")
    public String updateClient(Client client){
        clientService.save(client);
        return "redirect:/admin";
    }

    @GetMapping("/clientDelete/{id}")
    public String deleteClient(@PathVariable("id") Integer id){
        clientService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/orderEdit/{id}")
    public String editOrder(/*@PathVariable("idCar") Car car,
                            @PathVariable("idClient") Client client,*/
                            @PathVariable("id") Integer id, Model model){
        Order order= orderService.findOrderById(id);
        model.addAttribute("order",order);
        return "orderEdit";
    }

    @PostMapping("/orderEdit")
    public String updateOrder(Order order){
        order1Service.save(order);
        return "redirect:/admin";
    }

    @GetMapping("/admin/orderDelete/{id}")
    public String deleteOrder(@PathVariable("id") Integer id){
        orderService.deleteById(id);
        return "redirect:/admin";
    }

}