package com.ajrat.resource;

import com.ajrat.domain.Book;
import com.ajrat.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorInfo processNoSuchElementException(NoSuchElementException e) {
        log.info("from BookController - No such element exception: {}", e.getMessage());
        return new ErrorInfo("from BookController - No such element exception: " + e.getMessage());
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorInfo processNumberFormatException(NumberFormatException e) {
        log.info("from BookController - Number format invalid: {}", e.getMessage());
        return new ErrorInfo("from BookController - Number format invalid: " + e.getMessage());
    }

    @GetMapping("/title/{title}")
    public Book findBook(@PathVariable String title) {
        log.info("finding book from BookController with title {}", title);
        return bookService.findByTitle(title);
    }

    @GetMapping
    public List<Book> getBooks() {
        log.info("getting all books from BookController");
        return bookService.findAll();
    }

    @GetMapping("/{stringId}")
    public Book getBook(@PathVariable String stringId) {
        Integer id = Integer.valueOf(stringId);
        log.info("getting book from BookController with id {}", id);
        return bookService.findById(id);
    }

    @GetMapping("/save")
    public void saveBook(@RequestParam(name = "title") String title) {
        log.info("saving book from BookController with title trough method GET", title);
        bookService.saveByTitle(title);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBook(@RequestBody Book book) {
        log.info("saving book from BookController with id {} trough post", book.getId());
        bookService.save(book);
    }

    @PutMapping
    public void editBook(@RequestBody Book book) {
        log.info("editing book from BookController with id {}", book.getId());
        bookService.edit(book);
    }

    @DeleteMapping("/{id}")
    public void delBook(@PathVariable Integer id) {
        log.info("deleting book from BookController with id {}", id);
        bookService.delete(id);
    }
}
