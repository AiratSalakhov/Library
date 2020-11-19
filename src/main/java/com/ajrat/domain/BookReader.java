package com.ajrat.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Data
@Table(name = "book_readers")
public class BookReader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @Column(name = "issue_data")
    private ZonedDateTime issueData;

    @Column(name = "return_data")
    private ZonedDateTime returnData;
}
