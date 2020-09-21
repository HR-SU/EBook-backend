package com.shr.backend.daoimpl;

import com.shr.backend.dao.PurchaseStaticsDao;
import com.shr.backend.entity.PurchaseStatics;
import com.shr.backend.repository.PurchaseStaticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class PurchaseStaticsDaoImpl implements PurchaseStaticsDao {
    @Autowired
    private PurchaseStaticsRepository purchaseStaticsRepository;

    @Override
    public List<PurchaseStatics> purchaseStaticsByDate(Date start, Date end) {
        return purchaseStaticsRepository.purchaseStaticsByDate(start, end);
    }
}
