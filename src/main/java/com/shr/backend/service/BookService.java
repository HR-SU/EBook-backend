package com.shr.backend.service;

import com.shr.backend.entity.Book;

import java.util.List;

public interface BookService {
    Book findOne(Integer Id);
    List<Book> findAllBooks();
    String findBookDetailById(Integer Id);
    Integer update(Book book);
    void updateBookCover(Integer bookId, String base64);
    void deleteById(Integer Id);
}
