package com.shr.backend.serviceimpl;

import com.shr.backend.dao.BookSalesStaticsDao;
import com.shr.backend.dao.CustomerStaticsDao;
import com.shr.backend.dao.PurchaseStaticsDao;
import com.shr.backend.dao.UserDao;
import com.shr.backend.entity.BookSalesStatics;
import com.shr.backend.entity.CustomerStatics;
import com.shr.backend.entity.PurchaseStatics;
import com.shr.backend.service.StaticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StaticsServiceImpl implements StaticsService{
    @Autowired
    private CustomerStaticsDao customerStaticsDao;

    @Autowired
    private BookSalesStaticsDao bookSalesStaticsDao;

    @Autowired
    private PurchaseStaticsDao purchaseStaticsDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<CustomerStatics> customerStatics(Date start, Date end,
                                                 String userName) {
        Integer userId = userDao.findByName(userName).getUserId();
        return customerStaticsDao.customerStaticsByDate(start, end, userId);
    }

    @Override
    public List<BookSalesStatics> bookStatics(Date start, Date end) {
        return bookSalesStaticsDao.bookStaticsByDate(start, end);
    }

    @Override
    public List<PurchaseStatics> purchaseStatics(Date start, Date end) {
        return purchaseStaticsDao.purchaseStaticsByDate(start, end);
    }
}
