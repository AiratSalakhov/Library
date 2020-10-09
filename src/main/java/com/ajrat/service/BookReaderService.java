package com.ajrat.service;

import com.ajrat.domain.Book;
import com.ajrat.domain.BookReader;
import com.ajrat.repository.BookReaderRepository;
import com.ajrat.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookReaderService {
    private final BookReaderRepository repository;

    public void save(BookReader bookReader) {
        repository.save(bookReader);
        log.info("From service saving {}", bookReader);
    }

    public BookReader findById(Integer id) {
        log.info("From service findById {}", id);
        return repository.findById(id).get();
    }

    public Iterable<BookReader> findAll() {
        log.info("From service findAll");
        return repository.findAll();
    }

    public void delete(Integer id) {
        log.info("From service delete by id {}", id);
        repository.delete(repository.findById(id).get());
    }

}

