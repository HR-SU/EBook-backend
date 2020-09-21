package com.shr.backend.repository;

import com.shr.backend.entity.BookOther;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookOtherRepository extends MongoRepository<BookOther, Integer> {
    Optional<BookOther> findByBookId(Integer id);
    void deleteByBookId(Integer id);
}
