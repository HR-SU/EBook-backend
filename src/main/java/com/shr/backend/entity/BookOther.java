package com.shr.backend.entity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "book_others")
public class BookOther {
    private ObjectId id;
    private int bookId;
    private String bookCoverBase64;
    private String bookDetail;

    @Id
    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public String getBookCoverBase64() { return bookCoverBase64; }
    public void setBookCoverBase64(String bookCoverBase64) {
        this.bookCoverBase64 = bookCoverBase64;
    }

    public String getBookDetail() { return bookDetail; }
    public void setBookDetail(String bookDetail) { this.bookDetail = bookDetail; }
}
