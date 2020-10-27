package com.ajrat.resource;

import com.ajrat.domain.Reader;
import com.ajrat.service.ReaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/readers")
public class ReaderController {

    private final ReaderService readerService;

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorInfo processException(Exception e) {
        log.info("from ReaderController - No such element exception: {}", e.getMessage());
        return new ErrorInfo(e.getMessage());
    }

    @GetMapping
    public List<Reader> getReaders() {
        log.info("getting all readers from ReaderController");
        return readerService.findAll();
    }

    @GetMapping("/{id}")
    public Reader getReader(@PathVariable Integer id) {
        log.info("getting reader from ReaderController with id {}", id);
        return readerService.findById(id);
    }

    @GetMapping("/save")
    public void saveReader(@RequestParam String name) {
        readerService.saveByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createReader(@RequestBody Reader reader) {
        log.info("creating reader from ReaderController with id {} trough post", reader.getId());
        readerService.save(reader);
    }

    @PutMapping
    public void editReader(@RequestBody Reader reader) {
        log.info("editing reader from ReaderController with id {}", reader.getId());
        readerService.edit(reader);
    }

    @DeleteMapping("/{id}")
    public void delReader(@PathVariable Integer id) {
        log.info("deleting reader from ReaderController with id {}", id);
        readerService.delete(id);
    }
}
