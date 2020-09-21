package com.shr.backend.daoimpl;

import com.shr.backend.dao.CartItemDao;
import com.shr.backend.entity.CartItem;
import com.shr.backend.entity.Shopcart;
import com.shr.backend.repository.CartItemRepository;
import com.shr.backend.repository.ShopcartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Iterator;

@Repository
public class CartItemDaoImpl implements CartItemDao {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ShopcartRepository shopcartRepository;
    @Override
    public CartItem findOne(Integer Id) { return cartItemRepository.getOne(Id); }

    @Override
    public Boolean update(CartItem cartItem) {
        cartItemRepository.save(cartItem);
        return true;
    }

    @Override
    public Boolean deleteById(Integer Id) {
        if(!cartItemRepository.existsById(Id)) return false;
        CartItem cartItem = cartItemRepository.getOne(Id);
        cartItem.setShopcart(null);
        cartItem.setBook(null);
        cartItemRepository.save(cartItem);
        cartItemRepository.delete(cartItem);
        return true;
    }
}
