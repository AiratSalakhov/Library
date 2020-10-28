package com.ajrat.service;

import com.ajrat.domain.Reader;
import com.ajrat.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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
        return repository.findById(id).orElseThrow(
                () -> new NoSuchElementException(buildInfoThatReaderWithIdNotFound(id)));
    }

    public List<Reader> findAll() {
        log.info("From Reader service findAll");
        List<Reader> readerList = new ArrayList<>();
        repository.findAll().forEach(readerList::add);
        return readerList;
    }

    public void delete(Integer id) {
        log.info("From Reader service delete by id {}", id);
        repository.delete(repository.findById(id).orElseThrow(
                () -> new NoSuchElementException(buildInfoThatReaderWithIdNotFound(id))));
    }

    public void saveByName(String name) {
        log.info("From Reader service saving by name {}", name);
        Reader reader = new Reader();
        reader.setName(name);
        save(reader);
    }

    public void edit(Reader reader) {
        Reader readerOriginal = repository.findById(reader.getId()).orElseThrow(
                () -> new NoSuchElementException(buildInfoThatReaderWithIdNotFound(reader.getId())));
        readerOriginal.setName(reader.getName());
        save(readerOriginal);
    }

    private String buildInfoThatReaderWithIdNotFound(Integer id) {
        return "Reader with id=" + id + " not found!";
    }
}
