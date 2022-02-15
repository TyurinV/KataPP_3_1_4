-- это для добавления первого юзера со всеми ролями admin/123

INSERT INTO users.users (firstName, lastName, password, sex, smoker)
VALUES ('admin', 'admin', '$2a$10$EtEWiG1tGWS1asBmswEBr.77djC2N63eyowDfjRbh4Vl1UUKTeF3C', 'M', true);

INSERT INTO users.roles (role_name)
VALUES ('ROLE_ADMIN');

INSERT INTO users.roles (role_name)
VALUES ('ROLE_USER');

INSERT INTO users.user_role (user_id, role_id)
VALUES (1, 1);

INSERT INTO users.user_role (user_id, role_id)
VALUES (1, 2);
