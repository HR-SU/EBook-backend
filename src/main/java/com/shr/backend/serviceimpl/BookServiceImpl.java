package com.shr.backend.serviceimpl;

import com.shr.backend.dao.BookDao;
import com.shr.backend.dao.CartItemDao;
import com.shr.backend.entity.Book;
import com.shr.backend.entity.CartItem;
import com.shr.backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookDao bookDao;

    @Autowired
    CartItemDao cartItemDao;

    @Override
    public Book findOne(Integer Id) { return bookDao.findOne(Id); }

    @Override
    public List<Book> findAllBooks() {
        List<Book> bookList = bookDao.findAll();
        List<Book> bookList1 = new ArrayList<>();
        for(Book book : bookList) {
            if(book.getOnSale())
                bookList1.add(book);
        }
        return bookList1;
    }

    @Override
    public String findBookDetailById(Integer Id) {
        Book b = bookDao.findOne(Id);
        return b.getDetail();
    }

    @Override
    public Integer update(Book book) {
        book.setOnSale(true);
        return bookDao.update(book);
    }

    @Override
    public void updateBookCover(Integer bookId, String base64) {
        bookDao.updateBookCover(bookId, base64);
    }

    @Override
    public void deleteById(Integer Id) {
        Book book = bookDao.findOne(Id);
        List<CartItem> cartItemList = book.getCartItemList();
        for(CartItem cartItem : cartItemList) {
            cartItemDao.deleteById(cartItem.getItemId());
        }
        book.setCartItemList(null);
        book.setOnSale(false);
        bookDao.update(book);
    }
}
