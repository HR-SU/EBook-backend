package com.shr.backend.serviceimpl;

import com.shr.backend.dao.OrderItemDao;
import com.shr.backend.entity.OrderItem;
import com.shr.backend.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemDao orderItemDao;

    @Override
    public OrderItem findOne(Integer Id) { return orderItemDao.findOne(Id); }

    @Override
    public List<OrderItem> findAll() {
        return orderItemDao.findAll();
    }
}
