package com.shr.backend.daoimpl;

import com.shr.backend.dao.BookSalesStaticsDao;
import com.shr.backend.entity.BookSalesStatics;
import com.shr.backend.repository.BookSalesStaticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class BookSalesStaticsDaoImpl implements BookSalesStaticsDao {
    @Autowired
    private BookSalesStaticsRepository bookSalesStaticsRepository;

    @Override
    public List<BookSalesStatics> bookStaticsByDate(Date start, Date end) {
        return bookSalesStaticsRepository.bookStaticsByDate(start, end);
    }
}
