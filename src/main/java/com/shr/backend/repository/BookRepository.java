package com.shr.backend.repository;

import com.shr.backend.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByBookName(String name);
}
