package com.shr.backend.serviceimpl;

import com.shr.backend.dao.BookDao;
import com.shr.backend.dao.CartItemDao;
import com.shr.backend.dao.ShopcartDao;
import com.shr.backend.dao.UserDao;
import com.shr.backend.entity.Book;
import com.shr.backend.entity.CartItem;
import com.shr.backend.entity.Shopcart;
import com.shr.backend.entity.User;
import com.shr.backend.service.ShopcartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopcartServiceImpl implements ShopcartService {
    @Autowired
    private ShopcartDao shopcartDao;

    @Autowired
    private CartItemDao cartItemDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BookDao bookDao;

    @Override
    public Shopcart findOne(Integer Id) { return shopcartDao.findOne(Id); }

    @Override
    public Boolean addCartItem(String name, Integer bookId, Integer amount) {
        User user = userDao.findByName(name);
        Book book = bookDao.findOne(bookId);
        Shopcart shopcart = user.getShopcart();
        if(shopcart == null) {
            shopcart = new Shopcart();
        }
        List<CartItem> cartItemList = shopcart.getCartItemList();
        if(cartItemList == null) cartItemList = new ArrayList<>();
        for(CartItem cartItem : cartItemList) {
            if(cartItem.getBook().getBookId() == bookId) {
                cartItem.setAmount(cartItem.getAmount() + amount);
                cartItemDao.update(cartItem);
                return true;
            }
        }
        CartItem cartItem = new CartItem();
        cartItem.setBook(book);
        cartItem.setAmount(amount);
        cartItem.setShopcart(shopcart);
        cartItemList.add(cartItem);
        shopcart.setCartItemList(cartItemList);
        shopcart.setUser(user);
        user.setShopcart(shopcart);
        userDao.update(user);
        return true;
    }

    @Override
    public List<CartItem> findCartItems(String name) {
        return userDao.findByName(name).getShopcart().getCartItemList();
    }
}
