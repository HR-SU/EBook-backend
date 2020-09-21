package com.shr.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.shr.backend.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cart")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @PostMapping(value = "/delItem")
    @ResponseBody
    @CrossOrigin
    public void delCartItem(@RequestParam(value = "id") Integer Id) {
        cartItemService.deleteCartItem(Id);
    }

    @PostMapping(value = "/changeAmount")
    @ResponseBody
    @CrossOrigin
    public void changeAmount(@RequestBody JSONObject jsonObject) {
        Integer itemId = jsonObject.getInteger("itemId");
        Integer amount = jsonObject.getInteger("amount");
        cartItemService.changeAmount(itemId, amount);
    }
}
