package com.shr.backend.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shr.backend.entity.BookSalesStatics;
import com.shr.backend.entity.CustomerStatics;
import com.shr.backend.entity.PurchaseStatics;
import com.shr.backend.service.StaticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/statics")
public class StaticsController {
    @Autowired
    private StaticsService staticsService;

    @GetMapping(value = "/customer")
    @ResponseBody
    @CrossOrigin
    public String customerStatics(@RequestParam(value = "start") @DateTimeFormat(pattern="yyyy-MM-dd") Date start,
                           @RequestParam(value = "end") @DateTimeFormat(pattern="yyyy-MM-dd") Date end,
                           @RequestParam(value = "user") String userName) {
        List<CustomerStatics> customerStaticsList =
                staticsService.customerStatics(start, end, userName);
        JSONArray jsonArray = new JSONArray();
        for(CustomerStatics customerStatics : customerStaticsList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("bookId", customerStatics.getBookId());
            jsonObject.put("bookName", customerStatics.getBookName());
            jsonObject.put("spend", customerStatics.getSpend());
            jsonObject.put("amount", customerStatics.getAmount());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @GetMapping(value = "/admin/book")
    @ResponseBody
    @CrossOrigin
    public String bookStatics(@RequestParam(value = "start") @DateTimeFormat(pattern="yyyy-MM-dd") Date start,
                                  @RequestParam(value = "end") @DateTimeFormat(pattern="yyyy-MM-dd") Date end) {
        List<BookSalesStatics> bookSalesStaticsList =
                staticsService.bookStatics(start, end);
        JSONArray jsonArray = new JSONArray();
        for(BookSalesStatics bookSalesStatics : bookSalesStaticsList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("bookId", bookSalesStatics.getBookId());
            jsonObject.put("bookName", bookSalesStatics.getBookName());
            jsonObject.put("spend", bookSalesStatics.getSpend());
            jsonObject.put("amount", bookSalesStatics.getAmount());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @GetMapping(value = "/admin/user")
    @ResponseBody
    @CrossOrigin
    public String purchaseStatics(@RequestParam(value = "start") @DateTimeFormat(pattern="yyyy-MM-dd") Date start,
                              @RequestParam(value = "end") @DateTimeFormat(pattern="yyyy-MM-dd") Date end) {
        List<PurchaseStatics> purchaseStaticsList =
                staticsService.purchaseStatics(start, end);
        JSONArray jsonArray = new JSONArray();
        for(PurchaseStatics purchaseStatics : purchaseStaticsList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("bookId", purchaseStatics.getUserId());
            jsonObject.put("bookName", purchaseStatics.getUserName());
            jsonObject.put("spend", purchaseStatics.getSpend());
            jsonObject.put("amount", purchaseStatics.getAmount());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }
}
