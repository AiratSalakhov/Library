package com.ajrat.service;

import com.ajrat.domain.Book;
import com.ajrat.domain.Reader;
import com.ajrat.repository.BookRepository;
import com.ajrat.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository repository;

    public void save(Reader reader) {
        repository.save(reader);
        log.info("From service saving {}", reader);
    }

    public Reader findById(Integer id) {
        log.info("From service findById {}", id);
        return repository.findById(id).get();
    }

    public Iterable<Reader> findAll() {
        log.info("From service findAll");
        return repository.findAll();
    }

    public void delete(Integer id) {
        log.info("From service delete by id {}", id);
        repository.delete(repository.findById(id).get());
    }

}
