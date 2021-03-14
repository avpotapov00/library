create table if not exists reader
(

    card_number serial primary key,
    name        varchar(255) not null

);


create table if not exists book
(
    id       serial primary key,
    name     varchar(255) not null,
    code     varchar(64)  not null check (book.code <> ''),
    author   varchar(255),
    owner_card_number integer      references reader (card_number) on delete set null
);

create index if not exists book_code on book (code);

create table if not exists employee
(
    id       serial PRIMARY KEY,
    username varchar(128) not null unique,
    password varchar(128) not null
);