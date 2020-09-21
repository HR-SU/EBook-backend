package com.shr.backend.entity;

import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books", schema = "ebook")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "bookId")
public class Book {
    private int bookId;
    private String bookName;
    private String author;
    private String ISBN;
    private String detail;
    private int stock;
    private int price;
    private Boolean onSale;
    private BookOther bookOther;
    private List<OrderItem> orderItemList;
    private List<CartItem> cartItemList;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    @Column(nullable = false)
    public String getBookName() { return bookName; }
    public void setBookName(String bookName) { this.bookName = bookName; }

    public String getISBN() { return ISBN; }
    public void setISBN(String ISBN) { this.ISBN = ISBN; }

    public String getDetail() { return detail; }
    public void setDetail(String detail) { this.detail = detail; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public Boolean getOnSale() { return onSale; }
    public void setOnSale(Boolean onSale) { this.onSale = onSale; }

    @Transient
    public BookOther getBookOther() { return bookOther; }
    public void setBookOther(BookOther bookOther) { this.bookOther = bookOther; }

    @OneToMany(mappedBy = "book")
    public List<OrderItem> getOrderItemList() { return orderItemList; }
    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    @OneToMany(mappedBy = "book")
    public List<CartItem> getCartItemList() { return cartItemList; }
    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }
}
