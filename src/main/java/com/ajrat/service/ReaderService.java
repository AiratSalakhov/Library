package com.ajrat.service;

import com.ajrat.domain.Reader;
import com.ajrat.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository repository;

    public void save(Reader reader) {
        log.info("From Reader service saving {}", reader);
        repository.save(reader);
    }

    public Reader findById(Integer id) {
        log.info("From Reader service findById {}", id);
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Reader with id=" + id + " not found!"));
    }

    public Iterable<Reader> findAll() {
        log.info("From Reader service findAll");
        return repository.findAll();
    }

    public void delete(Integer id) {
        log.info("From Reader service delete by id {}", id);
        repository.delete(repository.findById(id).orElseThrow(() -> new NoSuchElementException("Reader for delete with id=" + id + " not found!")));
    }

}
