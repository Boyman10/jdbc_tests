CREATE TABLE country (
                         id   INTEGER      NOT NULL AUTO_INCREMENT,
                         name VARCHAR(128) NOT NULL,
                         PRIMARY KEY (id)
);

CREATE TABLE app_user (
                         id   INTEGER      NOT NULL AUTO_INCREMENT,
                         first_name VARCHAR(128) NOT NULL,
                         last_name VARCHAR(128) NOT NULL,
                         PRIMARY KEY (id)
);

