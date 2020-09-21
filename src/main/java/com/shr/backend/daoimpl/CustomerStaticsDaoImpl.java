package com.shr.backend.daoimpl;

import com.shr.backend.dao.CustomerStaticsDao;
import com.shr.backend.entity.CustomerStatics;
import com.shr.backend.repository.CustomerStaticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class CustomerStaticsDaoImpl implements CustomerStaticsDao {
    @Autowired
    private CustomerStaticsRepository customerStaticsRepository;

    @Override
    public List<CustomerStatics> customerStaticsByDate(Date start,
                                                       Date end, Integer userId) {
        return customerStaticsRepository.customerStaticsByDate(start, end, userId);
    }
}
