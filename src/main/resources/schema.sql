DROP TABLE IF EXISTS country;
DROP TABLE IF EXISTS app_user;

CREATE TABLE country
(
    id   integer      NOT NULL auto_increment,
    name VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE app_user
(
    id         INTEGER      NOT NULL auto_increment,
    first_name VARCHAR(128) NOT NULL,
    last_name  VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE app_user
    ADD UNIQUE (first_name, last_name);

