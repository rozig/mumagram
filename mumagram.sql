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
    filter varchar(100) NULL,
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
    status varchar(20) NOT NULL,
    created_date datetime NOT NULL,
    updated_date datetime NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES mumagram.user(id),
    FOREIGN KEY (follower_id) REFERENCES mumagram.user(id)
);

INSERT INTO mumagram.`user` (`firstname`,`lastname`,`email`,`username`,`password`,`salt`,`bio`,`profile_picture`,`is_private`,`created_date`,`updated_date`) VALUES ('Veeti ','Seppanen','veeti@gmail.com','veeti','880bab40c91f1749ef71cc9fbaea0f6e8eae598d135409701036be83402cadad18d18b2bcfe5526532fdbd7f43cd0944f1d966033ebfdcc72c19898b9cb334d4','87d26f1d19a714b7017e09493408d547',NULL,'https://mumagram.s3.us-east-2.amazonaws.com/veeti.jpg',0,'2018-03-20 00:00:00',NULL);
INSERT INTO mumagram.`user` (`firstname`,`lastname`,`email`,`username`,`password`,`salt`,`bio`,`profile_picture`,`is_private`,`created_date`,`updated_date`) VALUES ('Masen ','Compton','Masen@gmail.com','Masen','219ecb7c9d816f525b548c0dacbc0a1c3ef4fa745cda5979c02b9ba0a7461f2874b9b69c7f03feb212ae2f45a026d68c7c792fe9a73e596582f18b4bebaf029d','cc6ce251e9a032b8144b33340243d47b',NULL,'https://mumagram.s3.us-east-2.amazonaws.com/Masen.jpg',0,'2018-03-20 00:00:00',NULL);
INSERT INTO mumagram.`user` (`firstname`,`lastname`,`email`,`username`,`password`,`salt`,`bio`,`profile_picture`,`is_private`,`created_date`,`updated_date`) VALUES ('Carmen','Velasco','Carmen@gmail.com','Carmen','2371f4503084502392724f12cc8cd8ac6220edbaa46347c85939113a9a6474f722439f547e5feefbbffa672a0608d6910da34f87aa0f624d038a66d2c7149c68','e7bdfb294088ca073f6c86fe9ab1bf22',NULL,'https://mumagram.s3.us-east-2.amazonaws.com/Carmen.jpg',0,'2018-03-20 00:00:00',NULL);
INSERT INTO mumagram.`user` (`firstname`,`lastname`,`email`,`username`,`password`,`salt`,`bio`,`profile_picture`,`is_private`,`created_date`,`updated_date`) VALUES ('Melissa','Gomez','Melissa@gmail.com','Melissa','4d101df7769050d3d063a3d96af34541c95a6cc0f9a543ea8775febee287a64b188c4a4c1cf70cf6df5742139ffe34e25c2742ee9c4269ebce23311549265256','d9f7ae21d60dfdbdc2bd80be41fe5254',NULL,'https://mumagram.s3.us-east-2.amazonaws.com/Melissa.jpg',0,'2018-03-20 00:00:00',NULL);
INSERT INTO mumagram.`user` (`firstname`,`lastname`,`email`,`username`,`password`,`salt`,`bio`,`profile_picture`,`is_private`,`created_date`,`updated_date`) VALUES ('Rivka ','Cummings','Rivka@gmail.com','Rivka','6e246b5c394f277aa9ad83b7bd1ca58cd4f36ca12e7d84d00860723c0d515c8ef415c12d815ebb99a027bea1bc4a2e41130b049a9fe224231cef2b20f6668f90','c7d3b71ea8b56730c025ce8900b73b62',NULL,'https://mumagram.s3.us-east-2.amazonaws.com/Rivka.jpg',0,'2018-03-20 00:00:00',NULL);
INSERT INTO mumagram.`user` (`firstname`,`lastname`,`email`,`username`,`password`,`salt`,`bio`,`profile_picture`,`is_private`,`created_date`,`updated_date`) VALUES ('Iida ','Niskanen','Iida@gmail.com','Iida','aac8bbbc95e6c0fe7ba2fde8e2c450c0a8d4e7e80caaeda8021ea7a69d574af958df1fa27bf0c45700b5759070986edf06ded7f271c56f0d02992284cebfae1a','2d48b963c3cc7cf0ca96a55f17576d43',NULL,'https://mumagram.s3.us-east-2.amazonaws.com/Iida.jpg',0,'2018-03-20 00:00:00',NULL);
INSERT INTO mumagram.`user` (`firstname`,`lastname`,`email`,`username`,`password`,`salt`,`bio`,`profile_picture`,`is_private`,`created_date`,`updated_date`) VALUES ('Lamar ','Buckner','Lamar@gmail.com','IidaLamar','df16437475e6956c7855d051e81c306e06f46eba5e70183a3aa3e444d429c40a99d6a3d30795c23d4b046cf1d45960bacc5c9610a5420eac995c9890ca6c7664','da36dc15519c515e6342faed5461386c',NULL,'https://mumagram.s3.us-east-2.amazonaws.com/IidaLamar.jpeg',0,'2018-03-20 00:00:00',NULL);
INSERT INTO mumagram.`user` (`firstname`,`lastname`,`email`,`username`,`password`,`salt`,`bio`,`profile_picture`,`is_private`,`created_date`,`updated_date`) VALUES ('Sophia','Perez','Sophia@gmail.com','Sophia','0e64f4fe578aaf068a187567cc1993ea42d96ef594149c3e00ccd73adadaea49253b63c639273756d379a4d5cbe23e43188521ef7cb7cb6c6693952f9f2ccc1a','9c382071976a49a48e7cb40a73377092',NULL,'https://mumagram.s3.us-east-2.amazonaws.com/Sophia.jpg',0,'2018-03-20 00:00:00',NULL);
INSERT INTO mumagram.`user` (`firstname`,`lastname`,`email`,`username`,`password`,`salt`,`bio`,`profile_picture`,`is_private`,`created_date`,`updated_date`) VALUES ('Kendal','Gay','Kendal@gmail.com','Kendal','4149c8d9761aa2bb24eb7a4031ce76edb80993da89ec39c42ac6f96e330e883bf24e76c091800047c1e711b4291637525ddd8d042ec72e56fa9a80a4cab1af33','00db58b42dabecd80bcda8b3ac40193b',NULL,'https://mumagram.s3.us-east-2.amazonaws.com/Kendal.jpeg',0,'2018-03-20 00:00:00',NULL);
INSERT INTO mumagram.`user` (`firstname`,`lastname`,`email`,`username`,`password`,`salt`,`bio`,`profile_picture`,`is_private`,`created_date`,`updated_date`) VALUES ('Jesiah ','Neely','Jesiah@gmail.com','Jesiah','57498015086a86b9c862653a6c15643c3a95eeaed9918380f463d4a64ff6b7c24a28748d7bd5e8a22bf7c868a0ee37fdf094b13d0c4868a487aa09ff98e24b1a','2dbfbb9a0f4cae030394bdd35d862c9a',NULL,'https://mumagram.s3.us-east-2.amazonaws.com/Jesiah.jpg',0,'2018-03-20 00:00:00',NULL);
INSERT INTO mumagram.`user` (`firstname`,`lastname`,`email`,`username`,`password`,`salt`,`bio`,`profile_picture`,`is_private`,`created_date`,`updated_date`) VALUES ('Tanvi','Bishop','Tanvi@gmail.com','Tanvi','222f8cc6e262f8561ad1c9f321689c8c20bcc4e107a854e4b24854d7d81952c889f43026341fe8809037db513c76ae8348097b51851c93db5ca883031144318c','9a02381b9cb307b22efcb833265d77cd',NULL,'https://mumagram.s3.us-east-2.amazonaws.com/Tanvi.jpeg',0,'2018-03-20 00:00:00',NULL);

INSERT INTO mumagram.`user_followers` (`user_id`,`follower_id`,`status`,`created_date`,`updated_date`) VALUES (1,2,'following','2018-03-20 17:45:27',NULL);
INSERT INTO mumagram.`user_followers` (`user_id`,`follower_id`,`status`,`created_date`,`updated_date`) VALUES (1,3,'following','2018-03-20 17:50:04',NULL);
INSERT INTO mumagram.`user_followers` (`user_id`,`follower_id`,`status`,`created_date`,`updated_date`) VALUES (1,4,'following','2018-03-20 17:50:04',NULL);
INSERT INTO mumagram.`user_followers` (`user_id`,`follower_id`,`status`,`created_date`,`updated_date`) VALUES (1,5,'following','2018-03-20 17:50:04',NULL);
INSERT INTO mumagram.`user_followers` (`user_id`,`follower_id`,`status`,`created_date`,`updated_date`) VALUES (2,1,'following','2018-03-20 17:50:04',NULL);
INSERT INTO mumagram.`user_followers` (`user_id`,`follower_id`,`status`,`created_date`,`updated_date`) VALUES (2,3,'following','2018-03-20 17:50:04',NULL);
INSERT INTO mumagram.`user_followers` (`user_id`,`follower_id`,`status`,`created_date`,`updated_date`) VALUES (2,4,'following','2018-03-20 17:50:04',NULL);
INSERT INTO mumagram.`user_followers` (`user_id`,`follower_id`,`status`,`created_date`,`updated_date`) VALUES (2,5,'following','2018-03-20 17:50:04',NULL);
INSERT INTO mumagram.`user_followers` (`user_id`,`follower_id`,`status`,`created_date`,`updated_date`) VALUES (3,1,'following','2018-03-20 17:50:04',NULL);
INSERT INTO mumagram.`user_followers` (`user_id`,`follower_id`,`status`,`created_date`,`updated_date`) VALUES (3,4,'following','2018-03-20 17:50:04',NULL);
INSERT INTO mumagram.`user_followers` (`user_id`,`follower_id`,`status`,`created_date`,`updated_date`) VALUES (4,1,'following','2018-03-20 17:50:04',NULL);

INSERT INTO mumagram.`post` (`picture`,`description`,`filter`,`user_id`,`created_date`,`updated_date`) VALUES ('http://localhost:8080/mumagram/assets/images/post/1.jpg','Lorem Ipsum has been the industry standard dummy text ever since the 1500s, when an unknown printer took a galley','filter-aqua',1,'2018-03-20 18:41:25',NULL);
INSERT INTO mumagram.`post` (`picture`,`description`,`filter`,`user_id`,`created_date`,`updated_date`) VALUES ('http://localhost:8080/mumagram/assets/images/post/2.jpg','Lorem Ipsum has been the industry standard dummy text ever since the 1500s, when an unknown printer took a galley','',2,'2018-03-20 18:41:30',NULL);
INSERT INTO mumagram.`post` (`picture`,`description`,`filter`,`user_id`,`created_date`,`updated_date`) VALUES ('http://localhost:8080/mumagram/assets/images/post/3.jpg','Lorem Ipsum has been the industry standard dummy text ever since the 1500s, when an unknown printer took a galley','filter-charmes',3,'2018-03-20 18:41:30',NULL);
INSERT INTO mumagram.`post` (`picture`,`description`,`filter`,`user_id`,`created_date`,`updated_date`) VALUES ('http://localhost:8080/mumagram/assets/images/post/4.jpg','Lorem Ipsum has been the industry standard dummy text ever since the 1500s, when an unknown printer took a galley','filter-charmes',4,'2018-03-20 18:41:30',NULL);
INSERT INTO mumagram.`post` (`picture`,`description`,`filter`,`user_id`,`created_date`,`updated_date`) VALUES ('http://localhost:8080/mumagram/assets/images/post/5.jpg','Lorem Ipsum has been the industry standard dummy text ever since the 1500s, when an unknown printer took a galley','filter-lofi',5,'2018-03-20 18:41:30',NULL);
INSERT INTO mumagram.`post` (`picture`,`description`,`filter`,`user_id`,`created_date`,`updated_date`) VALUES ('http://localhost:8080/mumagram/assets/images/post/6.jpg','Lorem Ipsum has been the industry standard dummy text ever since the 1500s, when an unknown printer took a galley','filter-lofi',6,'2018-03-20 18:41:30',NULL);
INSERT INTO mumagram.`post` (`picture`,`description`,`filter`,`user_id`,`created_date`,`updated_date`) VALUES ('http://localhost:8080/mumagram/assets/images/post/7.jpg','Lorem Ipsum has been the industry standard dummy text ever since the 1500s, when an unknown printer took a galley','filter-inkwell',7,'2018-03-20 18:41:30',NULL);
INSERT INTO mumagram.`post` (`picture`,`description`,`filter`,`user_id`,`created_date`,`updated_date`) VALUES ('http://localhost:8080/mumagram/assets/images/post/2.jpg','Lorem Ipsum has been the industry standard dummy text ever since the 1500s, when an unknown printer took a galley','filter-inkwell',1,'2018-03-20 18:41:30',NULL);
INSERT INTO mumagram.`post` (`picture`,`description`,`filter`,`user_id`,`created_date`,`updated_date`) VALUES ('http://localhost:8080/mumagram/assets/images/post/3.jpg','Lorem Ipsum has been the industry standard dummy text ever since the 1500s, when an unknown printer took a galley','filter-reyes',1,'2018-03-20 18:41:30',NULL);
INSERT INTO mumagram.`post` (`picture`,`description`,`filter`,`user_id`,`created_date`,`updated_date`) VALUES ('http://localhost:8080/mumagram/assets/images/post/5.jpg','Lorem Ipsum has been the industry standard dummy text ever since the 1500s, when an unknown printer took a galley','filter-reyes',2,'2018-03-20 18:41:30',NULL);
INSERT INTO mumagram.`post` (`picture`,`description`,`filter`,`user_id`,`created_date`,`updated_date`) VALUES ('http://localhost:8080/mumagram/assets/images/post/6.jpg','Lorem Ipsum has been the industry standard dummy text ever since the 1500s, when an unknown printer took a galley','filter-reyes',2,'2018-03-20 18:41:30',NULL);