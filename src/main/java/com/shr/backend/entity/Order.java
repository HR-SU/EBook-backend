package com.shr.backend.entity;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "orders", schema = "ebook")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "orderId")
public class Order {
    private int orderId;
    private Date orderDate;
    private int totalSpend;
    private User user;
    private List<OrderItem> orderItemList;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    public int getTotalSpend() { return totalSpend; }
    public void setTotalSpend(int totalSpend) { this.totalSpend = totalSpend; }

    @ManyToOne
    @JsonIgnoreProperties(value = "orderList")
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JsonIgnoreProperties(value = "order")
    public List<OrderItem> getOrderItemList() { return orderItemList; }
    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
