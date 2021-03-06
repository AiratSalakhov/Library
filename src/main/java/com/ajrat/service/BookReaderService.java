package com.ajrat.service;

import com.ajrat.domain.Book;
import com.ajrat.domain.BookReader;
import com.ajrat.domain.Reader;
import com.ajrat.repository.BookReaderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookReaderService {

    private final BookReaderRepository repository;
    private final BookService bookService;
    private final ReaderService readerService;

    public void save(BookReader bookReader) {
        log.info("From BookReader service saving bookreader");
        repository.save(bookReader);
    }

    public BookReader findById(Integer id) {
        log.info("From BookReader service findById {}", id);
        return repository.findById(id).orElseThrow(
                () -> new NoSuchElementException(buildInfoThatBookReaderWithIdNotFound(id)));
    }

    public List<BookReader> findAll() {
        log.info("From BookReader service findAll");
        List<BookReader> bookReaderList = new ArrayList<>();
        repository.findAll().forEach(bookReaderList::add);
        return bookReaderList;
    }

    public void delete(Integer id) {
        log.info("From BookReader service delete by id {}", id);
        repository.delete(repository.findById(id).orElseThrow(
                () -> new NoSuchElementException(buildInfoThatBookReaderWithIdNotFound(id))));
    }

    public void edit(BookReader bookReader) {
        BookReader bookReaderOriginal = repository.findById(bookReader.getId()).orElseThrow(
                () -> new NoSuchElementException(buildInfoThatBookReaderWithIdNotFound(bookReader.getId())));
        bookReaderOriginal.setBook(bookReader.getBook());
        bookReaderOriginal.setReader(bookReader.getReader());
        bookReaderOriginal.setIssueData(bookReader.getIssueData());
        bookReaderOriginal.setReturnData(bookReader.getReturnData());
        save(bookReaderOriginal);
    }

    public void issueBook(Integer bookId, Integer readerId) {
        Book book = bookService.findById(bookId);
        Reader reader = readerService.findById(readerId);
        repository.findByBookAndReturnDataIsNull(book).ifPresent((b) -> {
            log.info(buildInfoThatBookIsAlreadyTaken(b));
            throw new RuntimeException(buildInfoThatBookIsAlreadyTaken(b));
        });
        BookReader bookReader = new BookReader();
        bookReader.setBook(book);
        bookReader.setReader(reader);
        bookReader.setIssueData(ZonedDateTime.now());
        save(bookReader);
    }

    private String buildInfoThatBookIsAlreadyTaken(BookReader b) {
        return "Book with id " + b.getBook().getId() +
                " and title " + b.getBook().getTitle() +
                " is already taken at " + b.getIssueData() +
                " and not returned back by reader " + b.getReader().getName();
    }

    private String buildInfoThatBookIsNotTakenYet(Integer bookId) {
        return "Book with id " + bookId +
                " is not taken yet and can't be returned!";
    }

    private String buildInfoThatBookReaderWithIdNotFound(Integer id) {
        return "BookReader with id=" + id +
                " not found!";
    }

    public void returnBook(Integer bookId) {
        Book book = bookService.findById(bookId);
        repository.findByBookAndReturnDataIsNull(book).ifPresentOrElse(
                (b) -> {
                    b.setReturnData(ZonedDateTime.now());
                    save(b);
                },
                () -> {
                    log.info(buildInfoThatBookIsNotTakenYet(bookId));
                    throw new RuntimeException(buildInfoThatBookIsNotTakenYet(bookId));
                });
    }
}
