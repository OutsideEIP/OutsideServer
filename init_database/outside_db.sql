DROP DATABASE IF EXISTS "outside";
CREATE DATABASE "outside";

\c "outside";
DROP TABLE IF EXISTS "Users";
CREATE TABLE "Users"
(
    id           serial,
    email        varchar(20) not null
        constraint users_pk
            primary key,
    password     varchar     not null,
    account_type varchar
    account_type varchar
);

CREATE UNIQUE INDEX users_email_uindex
    ON "Users" (email);

