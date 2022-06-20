package com.carrental.service;

import com.carrental.dao.model.Client;
import com.carrental.dao.model.Order;
import com.carrental.dao.model.OrderDto;
import com.carrental.dao.repository.CarRepository;
import com.carrental.dao.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ClientService clientService;
    private final CarRepository carService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ClientService clientService,CarRepository carService) {
        this.orderRepository = orderRepository;
        this.clientService = clientService;
        this.carService=carService;
    }

    @Override
    public void save(OrderDto orderDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client client = clientService.findClientByUsername(authentication.getName());
        Order order = new Order();
        order.setIdCar(carService.findById(orderDto.getId_car()).get());
        order.setIdClient(client);
        order.setDateOfIssue(orderDto.getDateOfIssue());
        order.setReturnDate(orderDto.getReturnDate());
        order.setRequestState("в обработке");
        orderRepository.save(order);

    }

    @Override
    public List<Order> findAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order findOrderById(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

}
