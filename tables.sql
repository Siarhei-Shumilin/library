CREATE TABLE users (
    id INTEGER AUTO_INCREMENT NOT NULL,
    name VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL,
    role ENUM("admin", "librarian", "reader"),
    PRIMARY KEY (id)
);


CREATE TABLE books (
    id INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
    title VARCHAR(30) NOT NULL,
    author VARCHAR(30) NOT NULL,
    genre VARCHAR(30) NOT NULL,
    description VARCHAR(500) NOT NULL,
    number_instances VARCHAR(30) NOT NULL,
    number_available_instances INTEGER NOT NULL
);


CREATE TABLE orders (
    id INTEGER AUTO_INCREMENT NOT NULL,
    user_id INTEGER NOT NULL,
    book_id INTEGER  NOT NULL,
    status ENUM("new", "active", "close"),
    date VARCHAR(30) NO NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);

insert into users(name,password,role) values ("admin","b0baee9d279d34fa1dfd71aadb908c3f","admin");
insert into users(name,password,role) values ("reader","827ccb0eea8a706c4c34a16891f84e7b","reader");
insert into users(name,password,role) values ("librarian","3d2172418ce305c7d16d4b05597c6a59","librarian");
