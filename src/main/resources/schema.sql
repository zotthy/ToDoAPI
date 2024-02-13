CREATE TABLE Category (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);
CREATE TABLE Task (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    due_date DATE,
    priority VARCHAR(50),
    status VARCHAR(50),
    category_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES Category(id)
);
CREATE TABLE app_user (
    id BIGINT PRIMARY KEY,
    name VARCHAR(30),
    surname VARCHAR(30),
    email VARCHAR(30),
    password VARCHAR(30)
    FOREIGN KEY()
);
CREATE TABLE Address(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    street VARCHAR(30),
    city VARCHAR(30),
    zip_code VARCHAR(10)
);
CREATE TABLE Assignment (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    task_id BIGINT,
    user_id BIGINT,
    FOREIGN KEY (task_id) REFERENCES Task(id),
    FOREIGN KEY (user_id) REFERENCES app_user(id)
);