package com.shr.backend.entity;

import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "orderItems", schema = "ebook")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "itemId")
public class OrderItem {
    private int itemId;
    private int amount;
    private int spend;
    private Book book;
    private Order order;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public int getSpend() { return spend; }
    public void setSpend(int spend) { this.spend = spend; }

    @ManyToOne
    @JsonIgnoreProperties(value = "orderItemList")
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

    @ManyToOne
    @JsonIgnoreProperties(value = "orderItemList")
    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
}
