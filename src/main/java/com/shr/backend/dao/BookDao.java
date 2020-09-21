package com.shr.backend.dao;

import com.shr.backend.entity.Book;
import java.util.List;

public interface BookDao {
    Book findOne(Integer Id);
    Book findByName(String name);
    Boolean insertOne(String bookName, String author, String ISBN,
                   Integer price, Integer stock, String detail);
    Boolean delById(Integer Id);
    Boolean updateById(Integer Id, String bookName, String author, String ISBN,
                       Integer price, Integer stock, String detail);
    Integer update(Book book);
    void updateBookCover(Integer bookId, String base64);
    List<Book> findAll();
    Boolean exists(Integer Id);
}
