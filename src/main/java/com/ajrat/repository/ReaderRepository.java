package com.ajrat.repository;

import com.ajrat.domain.Book;
import com.ajrat.domain.Reader;
import org.springframework.data.repository.CrudRepository;

public interface ReaderRepository extends CrudRepository<Reader, Integer> {
}
