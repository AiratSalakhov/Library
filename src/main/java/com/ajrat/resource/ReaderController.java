package com.ajrat.resource;

import com.ajrat.domain.Reader;
import com.ajrat.service.ReaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/readers")
public class ReaderController {

    private final ReaderService readerService;

    @GetMapping
    public List<Reader> getReaders() {
        List<Reader> readerList = new ArrayList<>();
        readerService.findAll().forEach(readerList::add);
        return readerList;
    }

    @GetMapping("/{id}")
    public Reader getReader(@PathVariable Integer id) {
        log.info("getting reader from controller with id {}", id);
        return readerService.findById(id);
    }

    @GetMapping("/save")
    public void saveReader(@RequestParam String name) {
        Reader reader = new Reader(null, name, null);
        readerService.save(reader);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createReader(@RequestBody Reader reader) {
        log.info("creating reader from controller with id {} trough post", reader.getId());
        readerService.save(reader);
    }

    @PutMapping
    public void editReader(@RequestBody Reader reader) {
        Reader readerOriginal = readerService.findById(reader.getId());
        readerOriginal.setName(reader.getName());
        readerService.save(readerOriginal);
    }

    @DeleteMapping("/{id}")
    public void delReader(@PathVariable Integer id) {
        log.info("deleting reader from controller with id {}", id);
        readerService.delete(id);
    }
}
