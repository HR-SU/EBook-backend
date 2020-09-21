package com.shr.backend.daoimpl;

import com.shr.backend.dao.BookDao;
import com.shr.backend.entity.Book;
import com.shr.backend.entity.BookOther;
import com.shr.backend.repository.BookOtherRepository;
import com.shr.backend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookOtherRepository bookOtherRepository;

    @Override
    public Book findOne(Integer Id) {
        Book book = bookRepository.getOne(Id);
        Optional<BookOther> bookOther = bookOtherRepository.findByBookId(Id);
        if(bookOther.isPresent()) book.setBookOther(bookOther.get());
        else book.setBookOther(null);
        return book;
    }

    @Override
    public Book findByName(String name) {
        Book book = bookRepository.findByBookName(name);
        Optional<BookOther> bookOther =
                bookOtherRepository.findByBookId(book.getBookId());
        if(bookOther.isPresent()) book.setBookOther(bookOther.get());
        else book.setBookOther(null);
        return book;
    }

    @Override
    public Boolean insertOne(String bookName, String author, String ISBN,
                             Integer price, Integer stock, String detail){
        if(price < 0 || stock < 0) return false;
        Book b = new Book();
        b.setBookName(bookName);
        b.setAuthor(author);
        b.setISBN(ISBN);
        b.setPrice(price);
        b.setStock(stock);
        b.setDetail(detail);
        bookRepository.save(b);
        return true;
    }

    @Override
    public Boolean delById(Integer Id) {
        if(!bookRepository.existsById(Id)) return false;
        bookOtherRepository.deleteByBookId(Id);
        bookRepository.deleteById(Id);
        return true;
    }

    @Override
    public Boolean updateById(Integer Id, String bookName, String author, String ISBN,
                      Integer price, Integer stock, String detail){
        if(!bookRepository.existsById(Id)) return false;
        Book b = bookRepository.getOne(Id);
        b.setBookName(bookName);
        b.setAuthor(author);
        b.setISBN(ISBN);
        b.setPrice(price);
        b.setStock(stock);
        b.setDetail(detail);
        bookRepository.save(b);
        return true;
    }

    @Override
    public Integer update(Book book) {
        Book book1 = bookRepository.saveAndFlush(book);
        int bookId = book1.getBookId();
        Optional<BookOther> bookOther = bookOtherRepository.findByBookId(bookId);
        if(bookOther.isPresent()) {
            bookOther.get().setBookDetail(book.getBookOther().getBookDetail());
            bookOtherRepository.save(bookOther.get());
        }
        else {
            BookOther newBookOther = new BookOther();
            newBookOther.setBookId(bookId);
            newBookOther.setBookDetail(book.getBookOther().getBookDetail());
            bookOtherRepository.save(newBookOther);
        }
        return book1.getBookId();
    }

    @Override
    public void updateBookCover(Integer bookId, String base64) {
        Optional<BookOther> bookOther = bookOtherRepository.findByBookId(bookId);
        if(bookOther.isPresent()) {
            bookOther.get().setBookCoverBase64(base64);
            bookOtherRepository.save(bookOther.get());
        }
        else {
            BookOther newBookOther = new BookOther();
            newBookOther.setBookId(bookId);
            newBookOther.setBookCoverBase64(base64);
            bookOtherRepository.save(newBookOther);
        }
    }

    @Override
    public List<Book> findAll() {
        List<Book> bookList = bookRepository.findAll();
        for(Book book : bookList) {
            Optional<BookOther> bookOther =
                    bookOtherRepository.findByBookId(book.getBookId());
            if(bookOther.isPresent()) book.setBookOther(bookOther.get());
            else book.setBookOther(null);
        }
        return bookList;
    }

    @Override
    public Boolean exists(Integer Id) {
        return bookRepository.existsById(Id);
    }
}
