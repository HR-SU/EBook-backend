package com.shr.backend.service;

import com.shr.backend.entity.CartItem;
import com.shr.backend.entity.Shopcart;

import java.util.List;

public interface ShopcartService {
    Shopcart findOne(Integer Id);
    Boolean addCartItem(String name, Integer bookId, Integer amount);
    List<CartItem> findCartItems(String name);
}
