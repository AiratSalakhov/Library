package com.ajrat.resource;

import com.ajrat.domain.Book;
import com.ajrat.domain.BookReader;
import com.ajrat.domain.Reader;
import com.ajrat.service.BookReaderService;
import com.ajrat.service.BookService;
import com.ajrat.service.ReaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/bookreaders")
public class BookReaderController {

    private final BookReaderService bookReaderService;
    private final BookService bookService;
    private final ReaderService readerService;


    @GetMapping("/issue")
    public void issueBook(@RequestParam Integer bookId, @RequestParam Integer readerId) {
        Book book = bookService.findById(bookId);
        Reader reader = readerService.findById(readerId);
        if (book.getBookReaders().stream().anyMatch(a -> a.getReturn_data() == null)) {
            log.info("Book with id {} is already taken", bookId);
            throw new RuntimeException("Book with id "+bookId+" is already taken!");
        }
        BookReader bookReader = new BookReader(null, book, reader, ZonedDateTime.now(), null);
        bookReaderService.save(bookReader);
    }

    @GetMapping("/return")
    public void issueBook(@RequestParam Integer bookId) {
        Book book = bookService.findById(bookId);
        if (book.getBookReaders().stream().noneMatch(a -> a.getReturn_data() == null)) {
            log.info("Book with id {} is not taken", bookId);
            throw new RuntimeException("Book with id "+bookId+" is not taken!");
        }
        for (BookReader bookReader : book.getBookReaders()) {
            if (bookReader.getReturn_data()==null) {
                bookReader.setReturn_data(ZonedDateTime.now());
                bookReaderService.save(bookReader);
                break;
            }
        }
    }

    @GetMapping
    public List<BookReader> getTakenBooks() {
        List<BookReader> bookReaderList = new ArrayList<>();
        bookReaderService.findAll().forEach(bookReaderList::add);
        return bookReaderList;
    }

    @GetMapping("/{id}")
    public BookReader getTakenBook(@PathVariable Integer id) {
        log.info("getting takenBook from controller with id {}", id);
        return bookReaderService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTakenBook(@RequestBody BookReader bookReader) {
        log.info("creating takenBook from controller with id {} trough post", bookReader.getId());
        bookReaderService.save(bookReader);
    }

    @PutMapping
    public void editTakenBook(@RequestBody BookReader bookReader) {
        BookReader bookReaderOriginal = bookReaderService.findById(bookReader.getId());
        bookReaderOriginal.setBook(bookReader.getBook());
        bookReaderOriginal.setReader(bookReader.getReader());
        bookReaderService.save(bookReaderOriginal);
    }

    @DeleteMapping("/{id}")
    public void delTakenBook(@PathVariable Integer id) {
        log.info("deleting takenBook from controller with id {}", id);
        bookReaderService.delete(id);
    }
}
