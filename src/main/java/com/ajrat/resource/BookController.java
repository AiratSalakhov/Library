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
    public ErrorInfo processException(Exception e) {
        log.info("from BookController - No such element exception: {}", e.getMessage());
        return new ErrorInfo(e.getMessage());
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

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Integer id) {
        log.info("getting book from BookController with id {}", id);
        return bookService.findById(id);
    }

    @GetMapping("/save")
    public void saveBook(@RequestParam String title) {
        log.info("saving book from BookController with title trough get", title);
        bookService.saveByTitle(title);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBook(@RequestBody Book book) {
        log.info("saving book from BookController with id {} trough post", book.getId());
        bookService.save(book);
    }

    @PostMapping("/id")
    public void testIntegerId(@RequestHeader Map<String, String> headers, @RequestBody String reqBody, @RequestParam(required = false) Integer id) {
        headers.forEach((key, value) -> {
            log.info(String.format("Header '%s' = %s", key, value));
        });
        log.info("Request body={}", reqBody);
        log.info("Integer id {} obtained trough post method", id);
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
