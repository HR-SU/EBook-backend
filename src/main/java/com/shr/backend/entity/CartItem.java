package com.shr.backend.entity;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "cartItems", schema = "ebook")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "itemId")
public class CartItem {
    private int itemId;
    private int amount;
    private Book book;
    private Shopcart shopcart;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    @ManyToOne
    @JsonIgnoreProperties(value = "cartItemList")
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

    @ManyToOne
    @JsonIgnoreProperties(value = "cartItemList")
    public Shopcart getShopcart() { return shopcart; }
    public void setShopcart(Shopcart shopcart) { this.shopcart = shopcart; }
}
