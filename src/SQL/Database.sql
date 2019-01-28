DROP TABLE IF EXISTS username;

CREATE TABLE username(
iteration binary,
salt binary,
date_of_birth int,
username VARCHAR (20),
password VARCHAR,
country VARCHAR (20),
real_name VARCHAR (50),
description VARCHAR (5000)
);


DROP TABLE IF EXISTS article;

CREATE TABLE article(

);