package com.ajrat.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "book_reader")
public class BookReader {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer bookId;
    private Integer readerId;
}
