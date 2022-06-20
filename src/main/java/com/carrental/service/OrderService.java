package com.carrental.service;

import com.carrental.dao.model.Order;
import com.carrental.dao.model.OrderDto;
import java.util.List;

public interface OrderService {
    void save(OrderDto order);
    List<Order> findAllOrder();
    void deleteById(Integer id);
    Order findOrderById(Integer id);
}
