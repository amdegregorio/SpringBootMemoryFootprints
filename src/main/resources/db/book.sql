CREATE TABLE IF NOT EXISTS book (
    id INT(11) AUTO_INCREMENT,
    title VARCHAR(255),
    author VARCHAR(100),
    isbn VARCHAR(20),
    PRIMARY KEY (id)
)