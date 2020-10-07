package com.ajrat.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="readers")
public class Reader {
    @Id
    private Integer id;
    private String name;
}
