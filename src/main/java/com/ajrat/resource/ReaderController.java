package com.ajrat.resource;

import com.ajrat.domain.Book;
import com.ajrat.domain.Reader;
import com.ajrat.service.BookService;
import com.ajrat.service.ReaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderService readerService;

    @GetMapping("/readers")
    public List<Reader> getReaders() {
        List<Reader> readerList = new ArrayList<>();
        readerService.findAll().forEach(readerList::add);
        return readerList;
    }

    @GetMapping("/readers/{id}")
    public Reader getReader(@PathVariable Integer id) {
        log.info("getting reader from controller with id {}", id);
        return readerService.findById(id);
    }

    @GetMapping("/saveReader")
    public void saveReader(@RequestParam Integer id, @RequestParam String name) {
        Reader reader = new Reader(id, name);
        readerService.save(reader);
    }

    @PostMapping("/readers")
    public void createReader(@RequestBody Reader reader) {
        log.info("creating reader from controller with id {} trough post", reader.getId());
        readerService.save(reader);
    }

    @PutMapping("/readers/{id}")
    public void editReader(@RequestBody Reader reader, @PathVariable Integer id) {
        Reader readerOriginal = readerService.findById(id);
        readerService.delete(id);
        readerOriginal.setId(reader.getId());
        readerOriginal.setName(reader.getName());
        readerService.save(readerOriginal);
    }

    @DeleteMapping("/readers/{id}")
    public void delReader(@PathVariable Integer id) {
        log.info("deleting reader from controller with id {}", id);
        readerService.delete(id);
    }
}
