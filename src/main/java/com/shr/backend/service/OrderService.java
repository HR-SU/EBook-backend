package com.shr.backend.service;

import com.shr.backend.entity.Order;
import com.shr.backend.entity.OrderItem;

import java.util.List;

public interface OrderService {
    Order findOne(Integer Id);
    List<Order> findAllOrders();
    List<OrderItem> findOrderItemByOrder(Integer orderId);
    Boolean placeOrder(String name);
    List<Order> findOrdersByUser(String name);

}
