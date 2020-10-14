package com.ajrat.repository;

import com.ajrat.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Integer> {
    public Optional<Book> findByTitle(String title);
}
