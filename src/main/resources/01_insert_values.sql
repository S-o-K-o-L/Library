INSERT INTO author (id, first_name, surname, patronymic)
VALUES (nextval('author_id_seq'),
'Иван',
'Грозный',
'Васильевич');



INSERT INTO book (id, name, description)
VALUES (nextval('book_id_seq'),
'Законы',
'Законы земли Русской');

INSERT INTO books_author (id, id_books, id_author)
VALUES (nextval('books_author_id_seq'), 1, 1);



INSERT INTO chapter (id, content, number, id_book)
VALUES (nextval('chapter_id_seq'),
'Нельзя убивать опричника!',
1,
1);

INSERT INTO users (id, first_name, last_name, login, password, telephone_number)
VALUES (nextval('users_id_seq'),
null,
null,
'qwer',
'123qwer',
'1234567');

INSERT INTO users_books(id, id_users, id_books)
VALUES (nextval('users_books_id_seq'), 1, 1);