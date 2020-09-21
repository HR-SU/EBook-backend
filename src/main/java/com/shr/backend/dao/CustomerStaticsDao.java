package com.shr.backend.dao;

import com.shr.backend.entity.CustomerStatics;

import java.util.Date;
import java.util.List;

public interface CustomerStaticsDao {
    List<CustomerStatics> customerStaticsByDate(Date start, Date end, Integer userId);
}
