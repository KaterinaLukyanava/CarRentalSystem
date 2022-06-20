package com.carrental.service;

import com.carrental.dao.model.Order;
import com.carrental.dao.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Order_ServiceImpl implements Order_Service {

    private final OrderRepository orderRepository;

    @Autowired
    public Order_ServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }
}
