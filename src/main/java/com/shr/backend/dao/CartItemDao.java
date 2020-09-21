package com.shr.backend.dao;

import com.shr.backend.entity.CartItem;

public interface CartItemDao {
    CartItem findOne(Integer Id);
    Boolean update(CartItem cartItem);
    Boolean deleteById(Integer Id);
}
