DROP TABLE IF EXISTS contacts;

CREATE TABLE contacts (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255),
                          email VARCHAR(255)
);