package com.ajrat.service;

import com.ajrat.domain.Book;
import com.ajrat.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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

    public void saveByTitle(String title) {
        log.info("From Book service saving by title {}", title);
        Book book = new Book();
        book.setTitle(title);
        save(book);
    }

    public Book findById(Integer id) {
        log.info("From Book service findById {}", id);
        return repository.findById(id).orElseThrow(
                () -> new NoSuchElementException(buildInfoThatBookWithIdNotFound(id)));
    }

    public List<Book> findAll() {
        log.info("From Book service findAll");
        List<Book> bookList = new ArrayList<>();
        repository.findAll().forEach(bookList::add);
        return bookList;
    }

    public void delete(Integer id) {
        log.info("From Book service delete by id {}", id);
        repository.delete(repository.findById(id).orElseThrow(
                () -> new NoSuchElementException(buildInfoThatBookWithIdNotFound(id))));
    }

    public Book findByTitle(String title) {
        log.info("From Book service findByTitle {}", title);
        return repository.findByTitle(title).orElseThrow(
                () -> new NoSuchElementException(buildInfoThatBookWithTitleNotFound(title)));
    }

    public void edit(Book book) {
        log.info("editing book from Book service with id {}", book.getId());
        Book bookOriginal = repository.findById(book.getId()).orElseThrow(
                () -> new NoSuchElementException(buildInfoThatBookWithIdNotFound(book.getId())));
        bookOriginal.setTitle(book.getTitle());
        save(bookOriginal);
    }

    private String buildInfoThatBookWithIdNotFound(Integer id) {
        return "Book with id=" + id + " not found!";
    }

    private String buildInfoThatBookWithTitleNotFound(String title) {
        return "Book with title=" + title + " not found!";
    }
}
