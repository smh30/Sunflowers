ALTER TABLE article DROP FOREIGN KEY article_ibfk_1;
ALTER TABLE comments DROP FOREIGN KEY comments_ibfk_1;
ALTER TABLE comments DROP FOREIGN KEY comments_ibfk_2;

DROP TABLE IF EXISTS user;

CREATE TABLE user(
iteration INT,
salt binary (32),
username VARCHAR (30),
password binary (64),
country VARCHAR (100),
real_name VARCHAR (50),
description VARCHAR (200),
image VARCHAR(200),
default_image VARCHAR (40) DEFAULT 'Default.jpg',
admin BOOLEAN,
date_of_birth VARCHAR (40),
use_default_image BOOLEAN,
email VARCHAR (100) NOT NULL DEFAULT 'none',
PRIMARY KEY (username)
);



DROP TABLE IF EXISTS article;

CREATE TABLE article(
article_title VARCHAR (200),
article_author VARCHAR (30),
article_id INT AUTO_INCREMENT,
article_body VARCHAR (10000),
article_timestamp TIMESTAMP ,
hidden BOOLEAN DEFAULT FALSE,
PRIMARY KEY (article_id),
FOREIGN KEY (article_author) REFERENCES user(username)
ON DELETE CASCADE
);


DROP TABLE IF EXISTS comments;

CREATE TABLE comments(
comments_id INT AUTO_INCREMENT,
comments_author VARCHAR (30),
coments_body VARCHAR (1000),
comments_timestamp TIMESTAMP ,
article_id INT,
parent_comment INT,
hidden BOOLEAN DEFAULT FALSE,
PRIMARY KEY (comments_id),
FOREIGN KEY (comments_author) REFERENCES user(username)
  ON DELETE CASCADE,
FOREIGN KEY (article_id) REFERENCES article(article_id)
  ON DELETE CASCADE,
  FOREIGN KEY (parent_comment) REFERENCES comments(comments_id)
  ON DELETE CASCADE
);



ALTER TABLE user ADD COLUMN image varchar(80);

ALTER TABLE comments ADD COLUMN parent_comment INT;
ALTER TABLE comments ADD FOREIGN KEY (parent_comment) REFERENCES comments (comments_id) ON DELETE CASCADE ;

-- # ALTER TABLE article DROP FOREIGN KEY article_ibfk_1;
-- # ALTER TABLE comments DROP FOREIGN KEY comments_ibfk_1;
-- # ALTER TABLE comments DROP FOREIGN KEY comments_ibfk_2;
-- #
-- # ALTER TABLE article ADD FOREIGN KEY (article_author) REFERENCES user(username) ON DELETE CASCADE;
-- # ALTER TABLE comments ADD FOREIGN KEY (comments_author) REFERENCES user(username) ON DELETE CASCADE;
-- # ALTER TABLE comments ADD FOREIGN KEY (article_id) REFERENCES article(article_id) ON DELETE CASCADE;

ALTER TABLE user ADD COLUMN admin BOOLEAN;

ALTER TABLE ysy.user DROP COLUMN date_of_birth;

ALTER TABLE ysy.user ADD COLUMN use_default_image BOOLEAN;

ALTER TABLE ysy.user ADD COLUMN email VARCHAR (100) NOT NULL DEFAULT 'none';

ALTER TABLE ysy.article ADD COLUMN hidden BOOLEAN DEFAULT FALSE;

ALTER TABLE ysy.comments ADD COLUMN hidden BOOLEAN DEFAULT FALSE;



DELETE FROM user;

DELETE FROM article;

DELETE FROM comments;



