CREATE TABLE recipient
(
    recipientId int NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    emailAddress varchar(100) NOT NULL,
     PRIMARY KEY (recipientId)
);

CREATE TABLE cic (
    cicId int NOT NULL AUTO_INCREMENT,
    type varchar(100) NOT NULL,
    subject varchar(100) NOT NULL,
    body varchar(100) NOT NULL,
    sourceSystem varchar(100) NOT NULL,
    createdDate TIMESTAMP,
    recipientId int NOT NULL,
    PRIMARY KEY (cicId),
    FOREIGN KEY (recipientId) REFERENCES recipient(recipientId),
);