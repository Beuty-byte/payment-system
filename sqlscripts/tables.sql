
CREATE DATABASE  payment_system CHARACTER SET utf8 COLLATE utf8_general_ci;
USE payment_system;

CREATE TABLE payment_system.users
(
    id       int NOT NULL AUTO_INCREMENT,
    name     varchar(14),
    surname  varchar(13),
	password varchar(40),
	email	 varchar(25),
	role     varchar(10) DEFAULT 'customer',
	PRIMARY KEY (id)
);

CREATE TABLE payment_system.bank_account
(
	id int NOT NULL AUTO_INCREMENT,
	balance double,
	is_blocked boolean,
	PRIMARY KEY (id)
);

CREATE TABLE payment_system.credit_card
(
    id    bigint NOT NULL AUTO_INCREMENT,
    users_id  int,
    bank_account_id   int,
    name varchar(20),
	PRIMARY KEY (id),
	FOREIGN KEY (users_id) REFERENCES users(id),
	FOREIGN KEY (bank_account_id) REFERENCES bank_account(id)
);



CREATE TABLE payment_system.payment
(
	id int NOT NULL AUTO_INCREMENT,
	users_id int,
	sum double,
	payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id),
	FOREIGN KEY (users_id) REFERENCES users(id)
);