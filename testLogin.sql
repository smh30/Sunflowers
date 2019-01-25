DROP TABLE IF EXISTS loginTable;

CREATE TABLE loginTable(
username VARCHAR(30),
pw_hash binary(64),
salt binary(32),
iterations INT,
PRIMARY KEY (username)
)