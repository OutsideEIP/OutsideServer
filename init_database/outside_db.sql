DROP DATABASE IF EXISTS "outside";
CREATE DATABASE "outside";

\c "outside";
DROP TABLE IF EXISTS "users";
CREATE TABLE "users"
(
    email        varchar(20) not null
        constraint users_pk
            primary key,
    token     varchar     not null,
    account_type varchar
);

CREATE UNIQUE INDEX users_email_uindex
    ON "users" (email);

