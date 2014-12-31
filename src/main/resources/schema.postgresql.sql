CREATE TABLE IF NOT EXISTS greetings (
    id SERIAL,
    author VARCHAR(30),
    text VARCHAR(50),
    PRIMARY KEY (id)
);