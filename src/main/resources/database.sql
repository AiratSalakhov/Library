CREATE TABLE if not exists books
(
id int NOT null PRIMARY KEY,
title varchar(100) NOT NULL
);

CREATE TABLE if not exists readers
(
id int NOT null PRIMARY KEY,
name varchar(100) NOT NULL
);

create table if not exists book_readers
(
id int primary key,
book_id int REFERENCES books (id),
reader_id int references readers(id)
);
