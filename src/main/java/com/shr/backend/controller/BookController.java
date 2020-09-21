package com.shr.backend.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shr.backend.entity.Book;
import com.shr.backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping(value = "/")
    @ResponseBody
    @CrossOrigin
    public String loadAllBooks() {
        System.out.println("finding all books");
        JSONArray jsonArray = new JSONArray();
        List<Book> bookList = bookService.findAllBooks();
        for(Book book : bookList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("bookId", book.getBookId());
            jsonObject.put("bookName", book.getBookName());
            jsonObject.put("author", book.getAuthor());
            jsonObject.put("isbn", book.getISBN());
            jsonObject.put("stock", book.getStock());
            jsonObject.put("price", book.getPrice());
            if(book.getBookOther() != null)
                jsonObject.put("bookCover", book.getBookOther().getBookCoverBase64());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @GetMapping(value = "/detail")
    @ResponseBody
    @CrossOrigin
    public String getDetail(@RequestParam(value = "id") Integer Id) {
        Book book = bookService.findOne(Id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("bookId", book.getBookId());
        jsonObject.put("bookName", book.getBookName());
        jsonObject.put("author", book.getAuthor());
        jsonObject.put("isbn", book.getISBN());
        jsonObject.put("stock", book.getStock());
        jsonObject.put("price", book.getPrice());
        if(book.getBookOther() != null) {
            jsonObject.put("bookCover", book.getBookOther().getBookCoverBase64());
        }
        if(book.getBookOther() != null) {
            jsonObject.put("detail", book.getBookOther().getBookDetail());
        }
        return jsonObject.toJSONString();
    }

    @PostMapping(value = "/modify")
    @ResponseBody
    @CrossOrigin
    public Integer editBook(@RequestBody Book book){
        return bookService.update(book);
    }

    @PostMapping(value = "/uploadCover")
    @ResponseBody
    @CrossOrigin
    public void uploadCover(@RequestBody JSONObject jsonObject) {
        bookService.updateBookCover(jsonObject.getInteger("bookId"),
                jsonObject.getString("base64"));
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    @CrossOrigin
    public void deleteBook(@RequestParam(value = "id") Integer Id) {
        bookService.deleteById(Id);
    }
}
