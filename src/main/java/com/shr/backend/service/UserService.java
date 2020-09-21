package com.shr.backend.service;

import com.shr.backend.entity.Authority;
import com.shr.backend.entity.CartItem;
import com.shr.backend.entity.Order;
import com.shr.backend.entity.User;

import java.util.List;

public interface UserService {
    User findOne(Integer Id);
    Boolean createCustomer(User user);
    List<User> getAllUser();
    void disableOrEnableUser(Integer Id, Boolean active);
    Boolean updateUser(User user);
    User findUserByName(String name);
    Boolean validate(String name, String password);
    Boolean isNameDuplicate(String name);
    Boolean placeOrder(String name);
    List<CartItem> findCartItems(String name);
    Boolean deleteCartItem(Integer Id);
    List<Order> findOrders(String name);
}
