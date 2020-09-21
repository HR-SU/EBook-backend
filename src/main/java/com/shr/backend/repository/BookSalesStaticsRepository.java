package com.shr.backend.repository;

import com.shr.backend.entity.BookSalesStatics;
import com.shr.backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface BookSalesStaticsRepository extends JpaRepository<Order, Integer> {
    @Query(value = "select new com.shr.backend.entity.BookSalesStatics(b.bookId , b.bookName, sum(a.amount), sum(a.spend))" +
            "from OrderItem a join Book b on a.book.bookId = b.bookId join Order c on a.order.orderId = c.orderId " +
            "where c.orderDate >= ?1 and c.orderDate <= ?2 " +
            "group by b.bookId ")
    List<BookSalesStatics> bookStaticsByDate(Date start, Date end);
}
