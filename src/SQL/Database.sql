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

INSERT INTO user (iteration, salt, date_of_birth, username, password, country, real_name, description)
VALUES()


DROP TABLE IF EXISTS article;

CREATE TABLE article(
article_title VARCHAR (30),
article_author VARCHAR (30),
article_id INT,
article_body VARCHAR (5000),
article_timestamp TIMESTAMP,
PRIMARY KEY (article_id, article_author),
FOREIGN KEY (article_author) REFERENCES user(username)
);

INSERT INTO article (article_title, article_author, article_id, article_body)
VALUES ('Cats', 'Harry', '1', 'Cats are cool'),
        ('Dogs', 'Ron', '2', 'Dogs are cooler than cats'),
        ('Kittens', 'Hermione', '3', 'Got a new kitten she rocks'),
        ('Birds', 'Harry', '4', 'Get an owl'),
        ('Rats', 'Ron', '5', 'Never again');


DROP TABLE IF EXISTS comments;

CREATE TABLE comments(
comments_id INT,
comments_author VARCHAR (30),
coments_body VARCHAR (200),
comments_timestamp TIMESTAMP,
article_id INT,
PRIMARY KEY (comments_id, comments_author, article_id),
FOREIGN KEY (comments_author) REFERENCES user(username),
FOREIGN KEY (article_id) REFERENCES article(article_id)
);

INSERT INTO comments(comments_id, comments_author, coments_body, article_id)
VALUES  ('1', 'Hermione', 'Ron you are so wrong', '2'),
        ('2', 'Harry', 'Guys, please be quiet', '2'),
        ('3', 'Harry', 'Good call Ron!', '5'),
        ('4', 'Ron', 'Little bit', '3'),
        ('5', 'Ron', 'LOL you are so luck bro', '4');