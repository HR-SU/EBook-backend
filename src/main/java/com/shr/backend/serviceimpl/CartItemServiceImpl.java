package com.shr.backend.serviceimpl;

import com.shr.backend.dao.CartItemDao;
import com.shr.backend.entity.CartItem;
import com.shr.backend.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemDao cartItemDao;

    @Override
    public CartItem findOne(Integer Id) { return cartItemDao.findOne(Id); }

    @Override
    public Boolean deleteCartItem(Integer Id) {
        return cartItemDao.deleteById(Id);
    }

    @Override
    public void changeAmount(Integer Id, Integer amount) {
        CartItem cartItem = cartItemDao.findOne(Id);
        cartItem.setAmount(amount);
        cartItemDao.update(cartItem);
    }
}
