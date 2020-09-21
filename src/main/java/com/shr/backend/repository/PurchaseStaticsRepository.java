package com.shr.backend.repository;

import com.shr.backend.entity.Order;
import com.shr.backend.entity.PurchaseStatics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface PurchaseStaticsRepository extends JpaRepository<Order, Integer> {
    @Query(value = "select new com.shr.backend.entity.PurchaseStatics(c.userId , c.userName, sum(a.amount), sum(a.spend))" +
            "from OrderItem a join Order b on a.order.orderId = b.orderId join User c on b.user.userId = c.userId " +
            "where b.orderDate >= ?1 and b.orderDate <= ?2 " +
            "group by c.userId ")
    List<PurchaseStatics> purchaseStaticsByDate(Date start, Date end);
}
