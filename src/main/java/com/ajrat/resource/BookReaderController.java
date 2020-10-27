package com.ajrat.resource;

import com.ajrat.domain.BookReader;
import com.ajrat.service.BookReaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/bookreaders")
public class BookReaderController {

    private final BookReaderService bookReaderService;

    @GetMapping("/issue")
    public void issueBook(@RequestParam Integer bookId, @RequestParam Integer readerId) {
        bookReaderService.issueBook(bookId, readerId);
    }

    @GetMapping("/return")
    public void returnBook(@RequestParam Integer bookId) {
        bookReaderService.returnBook(bookId);
    }

    @GetMapping
    public List<BookReader> getTakenBooks() {
        return bookReaderService.findAll();
    }

    @GetMapping("/{id}")
    public BookReader getTakenBook(@PathVariable Integer id) {
        log.info("getting takenBook from BookReaderController with id {}", id);
        return bookReaderService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTakenBook(@RequestBody BookReader bookReader) {
        log.info("creating takenBook from BookReaderController with id {} trough post", bookReader.getId());
        bookReaderService.save(bookReader);
    }

    @PutMapping
    public void editTakenBook(@RequestBody BookReader bookReader) {
        bookReaderService.edit(bookReader);
    }

    @DeleteMapping("/{id}")
    public void deleteTakenBook(@PathVariable Integer id) {
        log.info("deleting takenBook from BookReaderController with id {}", id);
        bookReaderService.delete(id);
    }
}
