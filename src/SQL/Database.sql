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
description VARCHAR (500),
image VARCHAR(80),
PRIMARY KEY (username)
);



DROP TABLE IF EXISTS article;

CREATE TABLE article(
article_title VARCHAR (100),
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
VALUES ('Cats', 'y', 'Cats are cool'),
        ('Dogs', 'y', 'Dogs are cooler than cats'),
        ('Kittens', 'y', 'Got a new kitten she rocks'),
        ('Birds', 'y', 'Get an owl'),
        ('Rats', 'y', 'Never again');

INSERT INTO comments(comments_author, coments_body, article_id)
VALUES  ('y', 'Ron you are so wrong', '2'),
        ('y', 'Guys, please be quiet', '2'),
        ('y', 'Good call Ron!', '5'),
        ('y', 'Little bit', '3'),
        ('y', 'LOL you are so lucky bro', '4');

ALTER TABLE user ADD COLUMN image varchar(80);

UPDATE user SET date_of_birth = 06121996, country='Australia', real_name='Pork Chop', description='Testing code is my sole hobby right now.' WHERE username = 'z';

UPDATE user SET date_of_birth = 09121995, country='NZ', real_name='Tulip', description='I am tired' WHERE username = 'y';


