package com.ajrat.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "readers")
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
