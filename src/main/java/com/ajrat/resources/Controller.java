package com.ajrat.resources;

import com.ajrat.domain.Book;
import com.ajrat.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Controller {

    private final BookService bookService;

    @GetMapping("/books")
    public List<Book> getBooks() {
        List<Book> bookList = new ArrayList<>();
        bookService.findAll().forEach(book -> bookList.add(book));
        return bookList;
    }
    @GetMapping("/saveBook")
    public void saveBook(Integer id, String title) {
        Book book = new Book(id, title);
        bookService.save(book);
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable Integer id) {
        log.info("getting book from controller with id {}", id);
        return bookService.findById(id);
    }

    @DeleteMapping("/books/{id}")
    public void delBook(@PathVariable Integer id) {
        log.info("deleting book from controller with id {}", id);
        bookService.delete(id);
    }


}
