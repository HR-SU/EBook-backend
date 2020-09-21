package com.shr.backend.daoimpl;

import com.shr.backend.dao.OrderDao;
import com.shr.backend.entity.Order;
import com.shr.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order findOne(Integer Id) { return orderRepository.getOne(Id); }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
