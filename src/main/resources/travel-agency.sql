DROP DATABASE IF EXISTS travel_agency;
CREATE DATABASE travel_agency;
USE travel_agency;

DROP TABLE IF EXISTS `agency`;
CREATE TABLE IF NOT EXISTS `agency`
(
    `id`      INT         NOT NULL auto_increment,
    `name`    VARCHAR(45) NOT NULL,
    `address` VARCHAR(45) NOT NULL,
    `email`   VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `hotel`;
CREATE TABLE IF NOT EXISTS `hotel`
(
    `id`         INT         NOT NULL auto_increment,
    `name`       VARCHAR(45) NOT NULL,
    `rating`     INT         NOT NULL,
    `country`    VARCHAR(45) NOT NULL,
    `city`       VARCHAR(45) NOT NULL,
    `room_count` INT         NOT NULL,
    `agency_id`  INT         NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `agency_id` FOREIGN KEY (`agency_id`) REFERENCES `agency` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user`
(
    `id`           INT         NOT NULL auto_increment,
    `first_name`   VARCHAR(45) NOT NULL,
    `second_name`  VARCHAR(45) NOT NULL,
    `user_role`    VARCHAR(45) NOT NULL,
    `phone_number` VARCHAR(45) NOT NULL,
    `email`        VARCHAR(45) NOT NULL,
    `password`     VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `room`;

CREATE TABLE IF NOT EXISTS `room`
(
    `id`        INT         NOT NULL auto_increment,
    `capacity`  INT         NOT NULL,
    `type`      VARCHAR(45) NOT NULL,
    `wifi`      BOOLEAN     NOT NULL,
    `breakfast` BOOLEAN     NOT NULL,
    `price`     DOUBLE      NOT NULL,
    `hotel_id`  INT         NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `hotel_id` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `booking`;

CREATE TABLE IF NOT EXISTS `booking`
(
    `id`      INT  NOT NULL auto_increment,
    `date`    DATE NOT NULL,
    `room_id` INT  NOT NULL,
    `user_id` INT  NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `room_id` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`),
    CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;




