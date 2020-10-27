package com.ajrat.repository;

import com.ajrat.domain.Book;
import com.ajrat.domain.BookReader;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookReaderRepository extends CrudRepository<BookReader, Integer> {
    Optional<BookReader> findByBookAndReturnDataIsNull(Book book);
}
