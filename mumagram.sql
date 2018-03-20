DROP DATABASE IF EXISTS mumagram;

CREATE DATABASE IF NOT EXISTS mumagram CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE mumagram.user (
    id int NOT NULL AUTO_INCREMENT,
    firstname varchar(191) NOT NULL,
    lastname varchar(191) NULL,
    email varchar(191) NOT NULL,
    username varchar(191) NOT NULL,
    password varchar(191) NOT NULL,
    salt varchar(191) NOT NULL,
    bio text NULL,
    profile_picture varchar(191) NULL,
    is_private boolean NOT NULL,
    created_date datetime NOT NULL,
    updated_date datetime NULL,
    PRIMARY KEY (id),
    UNIQUE (id, email, username)
);

CREATE TABLE mumagram.post (
    id int NOT NULL AUTO_INCREMENT,
    picture varchar(191) NOT NULL,
    description varchar(191) NULL,
    user_id int NOT NULL,
    created_date datetime NOT NULL,
    updated_date datetime NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES mumagram.user(id)
);

CREATE TABLE mumagram.comment (
    id int NOT NULL AUTO_INCREMENT,
    comment text NOT NULL,
    post_id int NOT NULL,
    user_id int NOT NULL,
    created_date datetime NOT NULL,
    updated_date datetime NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (post_id) REFERENCES mumagram.post(id),
    FOREIGN KEY (user_id) REFERENCES mumagram.user(id)
);

CREATE TABLE mumagram.`like` (
    id int NOT NULL AUTO_INCREMENT,
    post_id int NOT NULL,
    user_id int NOT NULL,
    created_date datetime NOT NULL,
    updated_date datetime NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (post_id) REFERENCES mumagram.post(id),
    FOREIGN KEY (user_id) REFERENCES mumagram.user(id)
);

CREATE TABLE mumagram.user_followers (
    id int NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL,
    follower_id int NOT NULL,
    created_date datetime NOT NULL,
    updated_date datetime NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES mumagram.user(id),
    FOREIGN KEY (follower_id) REFERENCES mumagram.user(id)
);