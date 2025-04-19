INSERT INTO users (username, password, enabled)
VALUES ('user', '{bcrypt}$2a$10$Dow1...hashedPassword...', true);

INSERT INTO authorities (username, authority)
VALUES ('user', 'ROLE_USER');
