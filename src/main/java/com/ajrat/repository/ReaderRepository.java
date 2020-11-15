package com.ajrat.repository;

import com.ajrat.domain.Reader;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ReaderRepository extends CrudRepository<Reader, Integer> {
    Optional<Reader> findByName(String name);
}
