INSERT INTO country (name)
VALUES ('France'),
       ('USA');
INSERT INTO city (name, country_id)
VALUES ('Reims', 1),
       ('Atlanta', 2);
INSERT INTO app_user (first_name, last_name)
VALUES ('Admin', 'User');
INSERT INTO app_user (first_name, last_name)
VALUES ('Bill', 'Man');
INSERT INTO app_company (name, identification, user_id)
VALUES ('Muy Company', '1234567', 2);