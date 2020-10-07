package com.ajrat.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Table(name = "books")
public class Book {
    @Id
    private Integer id;
    private String title;
}
