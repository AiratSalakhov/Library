package com.ajrat.service;

import com.ajrat.domain.Book;
import com.ajrat.repository.BookRepository;
import lombok.ToString;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void saveTest() {
        Book book = new Book(1, "Test name");
        bookService.save(book);
        Book savedBook = bookService.findById(book.getId());
        Assert.assertEquals(book, savedBook);
    }

    @TestConfiguration
    static class MyTestConfig {

        @Bean
        public BookService bookService(BookRepository bookRepository) {
            return new BookService(bookRepository);
        }
    }

}
