package com.ajrat.service;

import com.ajrat.domain.Book;
import com.ajrat.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;

    public void save(Book book) {
        log.info("From Book service saving {}", book);
        repository.save(book);
    }

    public Book findById(Integer id) {
        log.info("From Book service findById {}", id);
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Book with id=" + id + " not found!"));
    }

    public Iterable<Book> findAll() {
        log.info("From Book service findAll");
        return repository.findAll();
    }

    public void delete(Integer id) {
        log.info("From Book service delete by id {}", id);
        repository.delete(repository.findById(id).orElseThrow(() -> new NoSuchElementException("Book for delete with id=" + id + " not found!")));
    }

    public Book findByTitle(String title) {
        log.info("From Book service findByTitle {}", title);
        return repository.findByTitle(title).orElseThrow(() -> new NoSuchElementException("Book with title=" + title + " not found!"));
    }


}
