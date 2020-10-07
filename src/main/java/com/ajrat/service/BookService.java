package com.ajrat.service;

import com.ajrat.domain.Book;
import com.ajrat.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;

    public void save(Book book) {
        repository.save(book);
        log.info("From service saving {}", book);
    }

    public Book findById(Integer id) {
        log.info("From service findById {}", id);
        return repository.findById(id).get();
    }

    public Iterable<Book> findAll() {
        log.info("From service findAll");
        return repository.findAll();
    }

    public void delete(Integer id) {
        log.info("From service delete by id {}", id);
        repository.delete(repository.findById(id).get());
    }

}
