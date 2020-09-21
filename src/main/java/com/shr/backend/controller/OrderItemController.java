package com.shr.backend.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shr.backend.entity.OrderItem;
import com.shr.backend.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orderItem")
public class OrderItemController {
    @Autowired
    OrderItemService orderItemService;

    @GetMapping(value = "/admin")
    @ResponseBody
    @CrossOrigin
    public String getAllOrdersByBook() {
        List<OrderItem> orderItemList = orderItemService.findAll();
        JSONArray jsonArray = new JSONArray();
        for(OrderItem orderItem : orderItemList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("itemId", orderItem.getItemId());
            jsonObject.put("orderId", orderItem.getOrder().getOrderId());
            jsonObject.put("amount", orderItem.getAmount());
            jsonObject.put("bookName", orderItem.getBook().getBookName());
            jsonObject.put("orderDate", orderItem.getOrder().getOrderDate());
            jsonObject.put("userName", orderItem.getOrder().getUser().getUserName());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }
}
