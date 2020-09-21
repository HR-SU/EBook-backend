package com.shr.backend.daoimpl;

import com.shr.backend.dao.OrderItemDao;
import com.shr.backend.entity.OrderItem;
import com.shr.backend.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public OrderItem findOne(Integer Id) { return orderItemRepository.getOne(Id); }

    @Override
    public List<OrderItem> findAll() { return  orderItemRepository.findAll(); }
}
