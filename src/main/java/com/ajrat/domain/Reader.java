package com.ajrat.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "readers")
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public static UserDetails getUserDetailsFromReader(Reader reader) {
        return new org.springframework.security.core.userdetails.User(
                reader.getName(), reader.getPassword(),
                reader.getStatus().equals(Status.ACTIVE),
                reader.getStatus().equals(Status.ACTIVE),
                reader.getStatus().equals(Status.ACTIVE),
                reader.getStatus().equals(Status.ACTIVE),
                reader.getRole().getAuthorities()
        );
    }

}
