CREATE TABLE if not exists books
(
id serial NOT null PRIMARY KEY,
title varchar(100) NOT NULL
);

CREATE TABLE if not exists readers
(
id serial NOT null PRIMARY KEY,
name varchar(100) NOT NULL
);

create table if not exists book_readers
(
id serial primary key,
book_id int REFERENCES books (id),
reader_id int references readers(id),
issue_data timestamp NOT NULL,
return_data timestamp
);
