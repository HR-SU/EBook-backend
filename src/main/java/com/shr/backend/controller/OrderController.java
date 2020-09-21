package com.shr.backend.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shr.backend.entity.Order;
import com.shr.backend.entity.OrderItem;
import com.shr.backend.entity.User;
import com.shr.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping(value = "/placeorder")
    @ResponseBody
    @CrossOrigin
    public void placeOrder(@RequestBody User user) {
        orderService.placeOrder(user.getUserName());
    }

    @GetMapping(value = "/user")
    @ResponseBody
    @CrossOrigin
    public String loadOrders(@RequestParam(value = "user") String userName) {
        JSONArray jsonArray = new JSONArray();
        List<Order> orderList = orderService.findOrdersByUser(userName);
        for(Order order : orderList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("orderId", order.getOrderId());
            jsonObject.put("orderDate", order.getOrderDate());
            jsonObject.put("totalSpend", order.getTotalSpend());
            JSONArray jsonArray1 = new JSONArray();
            List<OrderItem> orderItemList = order.getOrderItemList();
            for(OrderItem orderItem : orderItemList) {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("itemId", orderItem.getItemId());
                jsonObject1.put("amount", orderItem.getAmount());
                jsonObject1.put("spend", orderItem.getSpend());
                jsonObject1.put("bookName", orderItem.getBook().getBookName());
                jsonObject1.put("author", orderItem.getBook().getAuthor());
                jsonArray1.add(jsonObject1);
            }
            jsonObject.put("orderItems", jsonArray1);
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @GetMapping(value = "/admin")
    @ResponseBody
    @CrossOrigin
    public String getAllOrders() {
        JSONArray jsonArray = new JSONArray();
        List<Order> orderList = orderService.findAllOrders();
        for(Order order : orderList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("orderId", order.getOrderId());
            jsonObject.put("orderDate", order.getOrderDate());
            jsonObject.put("totalSpend", order.getTotalSpend());
            jsonObject.put("userName", order.getUser().getUserName());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @GetMapping(value = "/admin/detail")
    @ResponseBody
    @CrossOrigin
    public String getOrderItem(@RequestParam Integer orderId) {
        Order order = orderService.findOne(orderId);
        List<OrderItem> orderItemList = orderService.findOrderItemByOrder(orderId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderId", order.getOrderId());
        jsonObject.put("orderDate", order.getOrderDate());
        jsonObject.put("totalSpend", order.getTotalSpend());
        JSONArray jsonArray = new JSONArray();
        for(OrderItem orderItem : orderItemList) {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("itemId", orderItem.getItemId());
            jsonObject1.put("amount", orderItem.getAmount());
            jsonObject1.put("spend", orderItem.getSpend());
            jsonObject1.put("bookName", orderItem.getBook().getBookName());
            jsonObject1.put("author", orderItem.getBook().getAuthor());
            jsonArray.add(jsonObject1);
        }
        jsonObject.put("orderItems", jsonArray);
        return jsonObject.toJSONString();
    }
}
