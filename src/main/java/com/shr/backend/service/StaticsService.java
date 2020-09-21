package com.shr.backend.service;

import com.shr.backend.entity.BookSalesStatics;
import com.shr.backend.entity.CustomerStatics;
import com.shr.backend.entity.PurchaseStatics;

import java.util.Date;
import java.util.List;

public interface StaticsService {
    List<CustomerStatics> customerStatics(Date start, Date end, String userName);
    List<BookSalesStatics> bookStatics(Date start, Date end);
    List<PurchaseStatics> purchaseStatics(Date start, Date end);
}
