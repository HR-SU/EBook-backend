package com.shr.backend.serviceimpl;

import com.shr.backend.dao.*;
import com.shr.backend.entity.*;
import com.shr.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthorityDao authorityDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private ShopcartDao shopcartDao;

    @Autowired
    private CartItemDao cartItemDao;

    @Override
    public User findOne(Integer Id) { return userDao.findOne(Id); }

    @Override
    public Boolean createCustomer(User user) {
        Authority authority = authorityDao.findByName("ROLE_CUSTOMER");
        user.setAuthority(authority);
        user.setActive(true);
        return userDao.update(user);
    }

    @Override
    public Boolean updateUser(User user) {
        return userDao.update(user);
    }

    @Override
    public User findUserByName(String name) { return userDao.findByName(name); }

    @Override
    public Boolean validate(String name, String password) {
        System.out.println("validating");
        User user = userDao.findByName(name);
        if(user == null) return false;
        return user.getPassword().equals(password);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.findAll();
    }

    @Override
    public void disableOrEnableUser(Integer Id, Boolean active) {
        User user = userDao.findOne(Id);
        user.setActive(active);
        userDao.update(user);
    }

    @Override
    public Boolean placeOrder(String name) {
        User user = userDao.findByName(name);
        Shopcart shopcart = user.getShopcart();
        List<CartItem> cartItemList = shopcart.getCartItemList();
        Order order = new Order();
        List<OrderItem> orderItemList = order.getOrderItemList();
        if(orderItemList == null) orderItemList = new ArrayList<>();
        for(CartItem cartItem : cartItemList) {
            OrderItem orderItem = new OrderItem();
            Book book = cartItem.getBook();
            orderItem.setBook(book);
            book.setStock(book.getStock() - 1);
            bookDao.update(book);
            orderItem.setAmount(cartItem.getAmount());
            orderItem.setSpend(cartItem.getBook().getPrice());
            orderItem.setOrder(order);
            orderItemList.add(orderItem);
        }
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
    public List<CartItem> findCartItems(String name) {
        return userDao.findByName(name).getShopcart().getCartItemList();
    }

    @Override
    public Boolean deleteCartItem(Integer Id) {
        return cartItemDao.deleteById(Id);
    }

    @Override
    public List<Order> findOrders(String name) {
        return userDao.findByName(name).getOrderList();
    }

    @Override
    public Boolean isNameDuplicate(String name) {
        List<User> userList = userDao.findAll();
        for(User user : userList) {
            if(user.getUserName().equals(name)) return true;
        }
        return false;
    }
}
