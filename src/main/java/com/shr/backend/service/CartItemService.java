package com.shr.backend.service;

import com.shr.backend.entity.CartItem;

public interface CartItemService {
    CartItem findOne(Integer Id);
    Boolean deleteCartItem(Integer Id);
    void changeAmount(Integer Id, Integer amount);
}
