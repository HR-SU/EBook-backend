package com.shr.backend.service;

import com.shr.backend.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    OrderItem findOne(Integer Id);
    List<OrderItem> findAll();
}
