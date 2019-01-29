ALTER TABLE article DROP FOREIGN KEY article_ibfk_1;
ALTER TABLE comments DROP FOREIGN KEY comments_ibfk_1;
ALTER TABLE comments DROP FOREIGN KEY comments_ibfk_2;

DROP TABLE IF EXISTS user;

CREATE TABLE user(
iteration INT,
salt binary (32),
date_of_birth INT,
username VARCHAR (20),
password binary (64),
country VARCHAR (20),
real_name VARCHAR (50),
description VARCHAR (100),
PRIMARY KEY (username)
);



DROP TABLE IF EXISTS article;

CREATE TABLE article(
article_title VARCHAR (30),
article_author VARCHAR (30),
article_id INT AUTO_INCREMENT,
article_body VARCHAR (5000),
article_timestamp TIMESTAMP,
PRIMARY KEY (article_id, article_author),
FOREIGN KEY (article_author) REFERENCES user(username)
);


DROP TABLE IF EXISTS comments;

CREATE TABLE comments(
comments_id INT AUTO_INCREMENT,
comments_author VARCHAR (30),
coments_body VARCHAR (200),
comments_timestamp TIMESTAMP,
article_id INT,
PRIMARY KEY (comments_id, comments_author, article_id),
FOREIGN KEY (comments_author) REFERENCES user(username),
FOREIGN KEY (article_id) REFERENCES article(article_id)
);




INSERT INTO article (article_title, article_author, article_body)
VALUES ('Cats', 'a', 'Cats are cool'),
        ('Dogs', 'b', 'Dogs are cooler than cats'),
        ('Kittens', 'c', 'Got a new kitten she rocks'),
        ('Birds', 'a', 'Get an owl'),
        ('Rats', 'b', 'Never again');

INSERT INTO comments(comments_author, coments_body, article_id)
VALUES  ( 'c', 'Ron you are so wrong', '2'),
        ( 'a', 'Guys, please be quiet', '2'),
        ('a', 'Good call Ron!', '5'),
        ( 'b', 'Little bit', '3'),
        ( 'b', 'LOL you are so lucky bro', '4');