package com.shr.backend.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shr.backend.entity.Book;
import com.shr.backend.entity.CartItem;
import com.shr.backend.service.BookService;
import com.shr.backend.service.ShopcartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cart")
public class ShopcartController {
    @Autowired
    private ShopcartService shopcartService;

    @Autowired
    private BookService bookService;

    @PostMapping(value = "/addcart")
    @ResponseBody
    @CrossOrigin
    public void addCart(@RequestBody JSONObject jsonObject) {
        String userName = jsonObject.getString("userName");
        Integer bookId = jsonObject.getInteger("bookId");
        Integer amount = jsonObject.getInteger("amount");
        shopcartService.addCartItem(userName, bookId, amount);
    }

    @GetMapping(value = "/shopcart")
    @ResponseBody
    @CrossOrigin
    public String loadShopcart(@RequestParam(value = "userName") String userName) {
        JSONArray jsonArray = new JSONArray();
        List<CartItem> cartItemList = shopcartService.findCartItems(userName);
        for(CartItem cartItem : cartItemList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("itemId", cartItem.getItemId());
            jsonObject.put("amount", cartItem.getAmount());
            Book book = bookService.findOne(cartItem.getBook().getBookId());
            jsonObject.put("bookName", book.getBookName());
            jsonObject.put("author", book.getAuthor());
            jsonObject.put("price", book.getPrice());
            if(book.getBookOther() != null)
                jsonObject.put("bookCover", book.getBookOther().getBookCoverBase64());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }
}
