DROP TABLE IF EXISTS username;

CREATE TABLE username(
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
article_id INT,
article_body VARCHAR (5000),
article_timestamp TIMESTAMP,
PRIMARY KEY (article_id, article_author),
FOREIGN KEY (article_author) REFERENCES ()
);


DROP TABLE IF EXISTS comments;

CREATE TABLE comments(
comments_id INT,
comments_author VARCHAR (30),
coments_body VARCHAR (200),
comments_timestamp TIMESTAMP,
article_id INT,
PRIMARY KEY (comments_id, comments_author, article_id),
FOREIGN KEY (comments_author) REFERENCES (),
FOREIGN KEY (article_id) REFERENCES 
);