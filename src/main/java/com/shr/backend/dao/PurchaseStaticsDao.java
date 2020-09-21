package com.shr.backend.dao;

import com.shr.backend.entity.PurchaseStatics;

import java.util.Date;
import java.util.List;

public interface PurchaseStaticsDao {
    List<PurchaseStatics> purchaseStaticsByDate(Date start, Date end);
}
