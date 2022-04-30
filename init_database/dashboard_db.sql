CREATE DATABASE /*!32312 IF NOT EXISTS*/ `share_food_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `share_food_db`;
DROP TABLE IF EXISTS `Users`;
create table Users
(
    uuid         varchar(20)  not null,
    phone_number varchar(20)  not null,
    email        varchar(100) null,
    first_name   varchar(100) null,
    last_name    varchar(100) null,
    password     varchar(100) not null,
    constraint users_pk
        primary key (uuid)
        unique (uuid)
        unique (phone_number)
        unique (uuid)
);


