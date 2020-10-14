package com.ajrat.service;

import com.ajrat.domain.BookReader;
import com.ajrat.repository.BookReaderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookReaderService {
    private final BookReaderRepository repository;

    public void save(BookReader bookReader) {
        log.info("From BookReader service saving bookreader");
        repository.save(bookReader);
    }

    public BookReader findById(Integer id) {
        log.info("From BookReader service findById {}", id);
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("BookReader with id=" + id + " not found!"));
    }

    public Iterable<BookReader> findAll() {
        log.info("From BookReader service findAll");
        return repository.findAll();
    }

    public void delete(Integer id) {
        log.info("From BookReader service delete by id {}", id);
        repository.delete(repository.findById(id).orElseThrow(() -> new NoSuchElementException("BookReader for delete with id=" + id + " not found!")));
    }

}

