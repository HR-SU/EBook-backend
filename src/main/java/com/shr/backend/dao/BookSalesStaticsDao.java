package com.shr.backend.dao;

import com.shr.backend.entity.BookSalesStatics;

import java.util.Date;
import java.util.List;

public interface BookSalesStaticsDao {
    List<BookSalesStatics> bookStaticsByDate(Date start, Date end);
}
