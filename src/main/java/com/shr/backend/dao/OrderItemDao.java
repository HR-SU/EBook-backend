package com.shr.backend.dao;

import com.shr.backend.entity.OrderItem;

import java.util.List;

public interface OrderItemDao {
    OrderItem findOne(Integer Id);
    List<OrderItem> findAll();
}
