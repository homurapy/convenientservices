INSERT INTO users (username, first_name,  last_name, password, is_activated, email, phone)
VALUES ('user', 'Jean', 'Billy', '$2a$10$iKNz2CivqvKERpJ9NvhcQetpSc0QnIj58t6szc8pk2GaA8gZcMCnW', 'true', 'info@mail.ru', '79065665656');

INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_CLIENT'),
       ('ROLE_MASTER'),
       ('ROLE_ADMIN');

INSERT INTO users_roles (user_id, role_id)
VALUES ('1', '2');
