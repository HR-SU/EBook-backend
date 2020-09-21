package com.shr.backend.entity;

public class BookSalesStatics {
    private Integer bookId;
    private String bookName;
    private Long amount;
    private Long spend;

    public BookSalesStatics(int bookId, String bookName, Long amount,
                           Long spend) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.amount = amount;
        this.spend = spend;
    }

    public Integer getBookId() { return this.bookId; }
    public void setBookId(Integer bookId) { this.bookId = bookId; }

    public String getBookName() { return bookName; }
    public void setBookName(String bookName) { this.bookName = bookName; }

    public Long getAmount() { return amount; }
    public void setAmount(Long amount) { this.amount = amount; }

    public Long getSpend() { return spend; }
    public void setSpend(Long spend) { this.spend = spend; }
}
