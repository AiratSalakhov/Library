package com.ajrat.resource;

import com.ajrat.domain.Reader;
import com.ajrat.domain.Role;
import com.ajrat.domain.Status;
import com.ajrat.service.ReaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/readers")
public class ReaderController {

    private final ReaderService readerService;
    private final PasswordEncoder passwordEncoder;

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorInfo processException(Exception e) {
        log.info("from ReaderController - No such element exception: {}", e.getMessage());
        return new ErrorInfo(e.getMessage());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('readers:read')")
    public List<Reader> getReaders() {
        log.info("getting all readers from ReaderController");
        return readerService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('readers:read')")
    public Reader getReader(@PathVariable Integer id) {
        log.info("getting reader from ReaderController with id {}", id);
        return readerService.findById(id);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('readers:write')")
    public void saveReader(@RequestParam("name") String name,
                           @RequestParam("password") String password,
                           @RequestParam("role") Role role,
                           @RequestParam("status") Status status) {
        if (!"ADMIN".equals(role.name()) && !"USER".equals(role.name())) {
            throw new RuntimeException("Bad role specified!");
        }
        if (!"ACTIVE".equals(status.name()) && !"BANNED".equals(status.name())) {
            throw new RuntimeException("Bad role specified!");
        }

        readerService.save(new Reader(null, name, passwordEncoder.encode(password), role, status));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('readers:write')")
    @ResponseStatus(HttpStatus.CREATED)
    public void createReader(@RequestBody Reader reader) {
        log.info("creating reader from ReaderController with id {} trough post", reader.getId());
        readerService.save(reader);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('readers:write')")
    public void editReader(@RequestBody Reader reader) {
        log.info("editing reader from ReaderController with id {}", reader.getId());
        readerService.edit(reader);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('readers:write')")
    public void delReader(@PathVariable Integer id) {
        log.info("deleting reader from ReaderController with id {}", id);
        readerService.delete(id);
    }
}
