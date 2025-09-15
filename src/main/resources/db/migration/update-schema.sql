CREATE SEQUENCE IF NOT EXISTS books_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS borrow_record_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS readers_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE books
(
    id                  BIGINT       NOT NULL,
    title               VARCHAR(255) NOT NULL,
    author              VARCHAR(255) NOT NULL,
    year_of_publication INTEGER      NOT NULL,
    isbn                VARCHAR(17)  NOT NULL,
    access              BOOLEAN      NOT NULL,
    CONSTRAINT pk_books PRIMARY KEY (id)
);

CREATE TABLE borrow_record
(
    id          BIGINT NOT NULL,
    book_id     BIGINT,
    reader_id   BIGINT,
    borrow_date TIMESTAMP WITHOUT TIME ZONE,
    return_date TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_borrow_record PRIMARY KEY (id)
);

CREATE TABLE readers
(
    id                   BIGINT       NOT NULL,
    name                 VARCHAR(255) NOT NULL,
    email                VARCHAR(255) NOT NULL,
    date_of_registration TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_readers PRIMARY KEY (id)
);

ALTER TABLE books
    ADD CONSTRAINT uc_books_isbn UNIQUE (isbn);

ALTER TABLE readers
    ADD CONSTRAINT uc_readers_email UNIQUE (email);

ALTER TABLE borrow_record
    ADD CONSTRAINT FK_BORROW_RECORD_ON_BOOK FOREIGN KEY (book_id) REFERENCES books (id);

ALTER TABLE borrow_record
    ADD CONSTRAINT FK_BORROW_RECORD_ON_READER FOREIGN KEY (reader_id) REFERENCES readers (id);