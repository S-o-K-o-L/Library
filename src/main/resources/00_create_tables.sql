CREATE TABLE Book
(
    Id          BIGSERIAL PRIMARY KEY,
    Name        VARCHAR(50) NOT NULL DEFAULT '',
    Description TEXT        NULL,
    Genres      TEXT[]      NULL
);

CREATE TABLE Chapter
(
    Id      BIGSERIAL PRIMARY KEY,
    Content TEXT    NOT NULL DEFAULT '',
    Number  INTEGER NOT NULL DEFAULT 0,
    Id_book INTEGER NOT NULL
);

ALTER TABLE Chapter
    ADD CONSTRAINT chapter_to_book_fkey
        FOREIGN KEY (Id_book) REFERENCES Book (Id)
            ON DELETE CASCADE;

CREATE TABLE Users
(
    Id               BIGSERIAL PRIMARY KEY,
    First_name       VARCHAR(30) NULL,
    Last_name        VARCHAR(30) NULL,
    Login            VARCHAR(50) NOT NULL DEFAULT 'login',
    Password         VARCHAR(50) NOT NULL DEFAULT 'password',
    Telephone_number VARCHAR(12) NULL
);

CREATE TABLE Users_books
(
    Id       BIGSERIAL PRIMARY KEY NOT NULL,
    Id_users INTEGER               NOT NULL,
    Id_books INTEGER               NOT NULL
);

ALTER TABLE Users_books
    ADD CONSTRAINT users_books_to_user_fkey
        FOREIGN KEY (Id_users) REFERENCES Users (Id)
            ON DELETE CASCADE;

ALTER TABLE Users_books
    ADD CONSTRAINT users_books_to_books_fkey
        FOREIGN KEY (Id_books) REFERENCES Book (Id)
            ON DELETE CASCADE;


CREATE TABLE Author
(
    Id         BIGSERIAL PRIMARY KEY,
    First_name VARCHAR(30) NOT NULL,
    Surname    VARCHAR(30) NOT NULL,
    Patronymic VARCHAR(30) NOT NULL
);

CREATE TABLE Books_author
(
    Id        BIGSERIAL PRIMARY KEY,
    Id_books  INTEGER NOT NULL,
    Id_author INTEGER NOT NULL
);

ALTER TABLE Books_author
    ADD CONSTRAINT books_author_to_book_fkey
        FOREIGN KEY (Id_books) REFERENCES Book (Id)
            ON DELETE CASCADE;

ALTER TABLE Books_author
    ADD CONSTRAINT books_author_to_author_fkey
        FOREIGN KEY (Id_author) REFERENCES Author (Id)
            ON DELETE CASCADE;
