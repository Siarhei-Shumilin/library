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
    number_instances VARCHAR(30) NOT NULL
);

CREATE TABLE books_issued (
    id INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
    title VARCHAR(30) NOT NULL,
    author VARCHAR(30) NOT NULL,
    genre VARCHAR(30) NOT NULL,
    book_id INTEGER  NOT NULL,
    FOREIGN KEY (book_id) REFERENCES books(id)
);

CREATE TABLE orders (
    id INTEGER AUTO_INCREMENT NOT NULL,
    user_id INTEGER NOT NULL,
    book_id INTEGER  NOT NULL,
    isActive Boolean NOT NULL,
    date VARCHAR(30) NO NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);

insert into books(title,author,genre,description, number_instances) values ("451° по Фаренгейту","Рей Брэдбери","Научная фантастика", "Мастер мирового масштаба, совмещающий в литературе несовместимое. Создатель таких ярчайших шедевров, как Марсианские хроники, 451° по Фаренгейту, Вино из одуванчиков и так далее и так далее. Лауреат многочисленных премий. Это Рэй Брэдбери.", 5);
insert into books(title,author,genre,description, number_instances) values ("1984","Джордж Оруэлл","Научная фантастика", "Своеобразный антипод второй великой антиутопии XX века - О дивный новый мир Олдоса Хаксли. Что, в сущности, страшнее: доведенное до абсурда общество потребления - или доведенное до абсолюта общество идеи? По Оруэллу, нет и не может быть ничего ужаснее тотальной несвободы...", 3);
insert into books(title,author,genre,description, number_instances) values ("Шантарам","Грегори Дэвид Робертс","Детектив", "Представляем читателю один из самых поразительных романов начала XXI века (в 2015 году получивший долгожданное продолжение – «Тень горы»). Эта преломленная в художественной форме исповедь человека, который сумел выбраться из бездны и уцелеть, разошлась по миру тиражом четыре миллиона экземпляров (из них полмиллиона – в России) и заслужила восторженные сравнения с произведениями лучших писателей Нового времени, от Мелвилла до Хемингуэя. ", 4);
insert into books(title,author,genre,description, number_instances) values ("Мастер и Маргарита","Михаил Афанасьевич Булгаков","Фэнтези", "Один из самых загадочных и удивительных романов XX века. Роман Мастер и Маргарита - визитная карточка Михаила Афанасьевича Булгакова. Более десяти лет Булгаков работал над книгой, которая стала его романом-судьбой, романом-завещанием. В Мастере и Маргарите есть все: веселое озорство и щемящая печаль, романтическая любовь и колдовское наваждение, магическая тайна и безрассудная игра с нечистой силой.", 3);
insert into books(title,author,genre,description, number_instances) values ("Три товарища","Эрих Мария Ремарк","Проза", "Трое друзей - Робби, отчаянный автогонщик Кестер и последний романтик Ленц прошли Первую мировую войну. Вернувшись в гражданскую жизнь, они основали небольшую автомастерскую. И хотя призраки прошлого преследуют их, они не унывают - ведь что может быть лучше дружбы, крепкой и верной, ради которой можно отдать последнее? Наверное, лишь только любовь, не знающая границ и пределов.", 3);

insert into users(name,password,role) values ("admin","11111","admin");
insert into users(name,password,role) values ("reader","12345","reader");
insert into users(name,password,role) values ("librarian","22222","librarian");
