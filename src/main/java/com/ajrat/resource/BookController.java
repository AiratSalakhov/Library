package com.ajrat.resource;

import com.ajrat.domain.Book;
import com.ajrat.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping("/title/{title}")
    public Book getBook(@PathVariable String title) {
        log.info("getting book from controller with title {}", title);
        return bookService.findByTitle(title);
    }

    @GetMapping
    public List<Book> getBooks() {
        List<Book> bookList = new ArrayList<>();
        bookService.findAll().forEach(bookList::add);
        return bookList;
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Integer id) {
        log.info("getting book from controller with id {}", id);
        return bookService.findById(id);
    }

    @GetMapping("/save")
    public void saveBook(@RequestParam String title) {
        Book book = new Book(null, title, null);
        bookService.save(book);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBook(@RequestBody Book book) {
        log.info("creating book from controller with id {} trough post", book.getId());
        bookService.save(book);
    }

    @PutMapping
    public void editBook(@RequestBody Book book) {
        Book bookOriginal = bookService.findById(book.getId());
        bookOriginal.setTitle(book.getTitle());
        bookService.save(bookOriginal);
    }

    @DeleteMapping("/{id}")
    public void delBook(@PathVariable Integer id) {
        log.info("deleting book from controller with id {}", id);
        bookService.delete(id);
    }
}
