package com.ajrat.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "readers")
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "reader", fetch = FetchType.LAZY)
    private Set<BookReader> bookReaders;

}
