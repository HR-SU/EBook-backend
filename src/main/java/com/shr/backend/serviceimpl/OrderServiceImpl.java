package com.shr.backend.serviceimpl;

import com.shr.backend.dao.BookDao;
import com.shr.backend.dao.OrderDao;
import com.shr.backend.dao.ShopcartDao;
import com.shr.backend.dao.UserDao;
import com.shr.backend.entity.*;
import com.shr.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private ShopcartDao shopcartDao;

    @Override
    public Order findOne(Integer Id) { return orderDao.findOne(Id); }

    @Override
    public List<Order> findAllOrders() { return orderDao.findAll(); }

    @Override
    public List<OrderItem> findOrderItemByOrder(Integer orderId) {
        Order order = orderDao.findOne(orderId);
        return order.getOrderItemList();
    }

    @Override
    public Boolean placeOrder(String name) {
        User user = userDao.findByName(name);
        Shopcart shopcart = user.getShopcart();
        List<CartItem> cartItemList = shopcart.getCartItemList();
        Order order = new Order();
        List<OrderItem> orderItemList = order.getOrderItemList();
        if(orderItemList == null) orderItemList = new ArrayList<>();
        int totalSpend = 0;
        for(CartItem cartItem : cartItemList) {
            OrderItem orderItem = new OrderItem();
            Book book = cartItem.getBook();
            book = bookDao.findOne(book.getBookId());
            orderItem.setBook(book);
            book.setStock(book.getStock() - cartItem.getAmount());
            bookDao.update(book);
            orderItem.setAmount(cartItem.getAmount());
            int spend = cartItem.getAmount() * cartItem.getBook().getPrice();
            orderItem.setSpend(spend);
            totalSpend += spend;
            orderItem.setOrder(order);
            orderItemList.add(orderItem);
        }
        order.setTotalSpend(totalSpend);
        order.setOrderItemList(orderItemList);
        order.setOrderDate(new Date());
        order.setUser(user);
        List<Order> orderList = user.getOrderList();
        if(orderList == null) orderList = new ArrayList<>();
        orderList.add(order);
        user.setOrderList(orderList);
        for(CartItem cartItem : cartItemList) {
            cartItem.setShopcart(null);
        }
        shopcart.setCartItemList(new ArrayList<>());
        shopcartDao.update(shopcart);
        userDao.update(user);
        return true;
    }

    @Override
    public List<Order> findOrdersByUser(String name) {
        return userDao.findByName(name).getOrderList();
    }
}
