package com.shr.backend.dao;

import com.shr.backend.entity.Order;

import java.util.List;

public interface OrderDao {
    Order findOne(Integer Id);
    List<Order> findAll();
}
