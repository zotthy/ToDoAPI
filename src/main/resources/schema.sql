CREATE TABLE db.category
(
    id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE task
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    title       VARCHAR(255) NOT NULL,
    description TEXT,
    due_date    DATE,
    priority    VARCHAR(50),
    status      VARCHAR(50),
    category_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES category (id)
);

CREATE TABLE address
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT,
    street   VARCHAR(30),
    city     VARCHAR(30),
    zip_code VARCHAR(10)
);

CREATE TABLE role
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30)
);

CREATE TABLE members
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(30)        NOT NULL,
    surname    VARCHAR(30)        not NULL,
    email      VARCHAR(30) UNIQUE NOT NULL,
    password   VARCHAR(200)        NOT NULL,
    address_id BIGINT,
    FOREIGN KEY (address_id) REFERENCES address (id)
);

CREATE TABLE user_roles
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    members_id BIGINT NOT NULL,
    role_id    BIGINT NOT NULL,
    FOREIGN KEY (members_id) REFERENCES members (id),
    FOREIGN KEY (role_id) REFERENCES role (id)
);

CREATE TABLE assignment
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    task_id    BIGINT,
    members_id BIGINT,
    FOREIGN KEY (task_id) REFERENCES task (id),
    FOREIGN KEY (members_id) REFERENCES members (id)
);