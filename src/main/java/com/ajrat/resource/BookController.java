package com.ajrat.resource;

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
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public List<Book> getBooks() {
        List<Book> bookList = new ArrayList<>();
        bookService.findAll().forEach(bookList::add);
        return bookList;
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable Integer id) {
        log.info("getting book from controller with id {}", id);
        return bookService.findById(id);
    }

    @GetMapping("/saveBook")
    public void saveBook(@RequestParam Integer id, @RequestParam String title) {
        Book book = new Book(id, title);
        bookService.save(book);
    }

    @PostMapping("/books")
    public void createBook(@RequestBody Book book) {
        log.info("creating book from controller with id {} trough post", book.getId());
        bookService.save(book);
    }

    @PutMapping("/books/{id}")
    public void editBook(@RequestBody Book book, @PathVariable Integer id) {
        Book bookOriginal = bookService.findById(id);
        bookService.delete(id);
        bookOriginal.setId(book.getId());
        bookOriginal.setTitle(book.getTitle());
        bookService.save(bookOriginal);
    }

    @DeleteMapping("/books/{id}")
    public void delBook(@PathVariable Integer id) {
        log.info("deleting book from controller with id {}", id);
        bookService.delete(id);
    }
}
