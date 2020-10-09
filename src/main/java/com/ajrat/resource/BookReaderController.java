package com.ajrat.resource;

import com.ajrat.domain.Book;
import com.ajrat.domain.BookReader;
import com.ajrat.service.BookReaderService;
import com.ajrat.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BookReaderController {

    private final BookReaderService bookReaderService;


    @GetMapping("/issue")
    public void issueBook()

    @GetMapping("/takenBooks")
    public List<BookReader> getTakenBooks() {
        List<BookReader> bookReaderList = new ArrayList<>();
        bookReaderService.findAll().forEach(bookReaderList::add);
        return bookReaderList;
    }

    @GetMapping("/takenBooks/{id}")
    public BookReader getTakenBook(@PathVariable Integer id) {
        log.info("getting takenBook from controller with id {}", id);
        return bookReaderService.findById(id);
    }

    @GetMapping("/saveTakenBook")
    public void saveTakenBook(@RequestParam Integer id, @RequestParam Integer bookId, @RequestParam Integer readerId) {
        BookReader bookReader = new BookReader(id, bookId, readerId);
        bookReaderService.save(bookReader);
    }

    @PostMapping("/takenBooks")
    public void createTakenBook(@RequestBody BookReader bookReader) {
        log.info("creating takenBook from controller with id {} trough post", bookReader.getId());
        bookReaderService.save(bookReader);
    }

    @PutMapping("/takenBooks/{id}")
    public void editTakenBook(@RequestBody BookReader bookReader, @PathVariable Integer id) {
        BookReader bookReaderOriginal = bookReaderService.findById(id);
        bookReaderService.delete(id);
        bookReaderOriginal.setId(bookReader.getId());
        bookReaderOriginal.setBookId(bookReader.getBookId());
        bookReaderOriginal.setReaderId(bookReader.getReaderId());
        bookReaderService.save(bookReaderOriginal);
    }

    @DeleteMapping("/takenBooks/{id}")
    public void delTakenBook(@PathVariable Integer id) {
        log.info("deleting takenBook from controller with id {}", id);
        bookReaderService.delete(id);
    }
}
