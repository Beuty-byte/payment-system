INSERT INTO users(name,surname,password,email) VALUES ('Сергей', 'Трофимов', 'd8811fb3492ff189caab9c7a7d0fccf3c284d27f','tree17@mail.ru');            
INSERT INTO users(name,surname,password,email) VALUES ('Евгений', 'Александров', '5f1c31dc92dbe46354f8075a3c7232ab2ee27d88','frostfox17@mail.ru');		
INSERT INTO users(name,surname,password,email) VALUES ('Николай', 'Петров', '7ff028606c134c868904bbceda7fd13885058910','get17@mail17@mail.ru');				
INSERT INTO users(name,surname,password,email) VALUES ('Елена', 'Прошкина', 'de629efcfd2476e79c0b27eaeed8f0301724ba5d','qWeRty321@mail.ru');				
INSERT INTO users(name,surname,password,email, role) VALUES ('Алексей', 'Никифоров', '60884edeebc5b41a9fad321407d2b79f5fbe1a6d','week@mail.ru','admin');			
INSERT INTO users(name,surname,password,email) VALUES ('Евгений', 'Звянцев', 'b460df72f691720c78374de164a61f40f552db89','gjoldkf17@mail.ru');			
INSERT INTO users(name,surname,password,email) VALUES ('Андрей', 'Северинцев', '6946f5619db9e6ee644c851b9e7798fc7add1042','kmfsdgv17@mail.ru');			
INSERT INTO users(name,surname,password,email) VALUES ('Илья', 'Пронин', '4e2ea9c51de07932189b7d9efc5f731cbf9c9971','ijjklmsad17@mail.ru');				
INSERT INTO users(name,surname,password,email) VALUES ('Владимир', 'Калинович', 'bcc1545b57deff999c35e6947d124edcb9b42227','jkssad@mail.ru');			
INSERT INTO users(name,surname,password,email) VALUES ('Анатолий', 'Антонов', 'a0fd7c2b7416332d6b06752c7da5efee1e4915b6','dgbbsx17@mail.ru');			
INSERT INTO users(name,surname,password,email) VALUES ('Виктор', 'Островский', 'efe2ca9bdbb0075d31c43853cde23ef7d7480200','jkasdas17@mail.ru');			


INSERT INTO bank_account(balance, is_blocked) VALUES (783.99, false);
INSERT INTO bank_account(balance, is_blocked) VALUES (5483.19, true);
INSERT INTO bank_account(balance, is_blocked) VALUES (1783.99, false);
INSERT INTO bank_account(balance, is_blocked) VALUES (15433.10, true);
INSERT INTO bank_account(balance, is_blocked) VALUES (871.13, false);
INSERT INTO bank_account(balance, is_blocked) VALUES (54325.5, true);
INSERT INTO bank_account(balance, is_blocked) VALUES (435123.11, false);
INSERT INTO bank_account(balance, is_blocked) VALUES (8371.90, true);
INSERT INTO bank_account(balance, is_blocked) VALUES (83.99, false);
INSERT INTO bank_account(balance, is_blocked) VALUES (78.99, true);
INSERT INTO bank_account(balance, is_blocked) VALUES (11.99, false);
INSERT INTO bank_account(balance, is_blocked) VALUES (8431.99, true);
INSERT INTO bank_account(balance, is_blocked) VALUES (98132.99, false);
INSERT INTO bank_account(balance, is_blocked) VALUES (1000000.99, true);
INSERT INTO bank_account(balance, is_blocked) VALUES (1443.14, false);


INSERT INTO credit_card(id, users_id, bank_account_id, name) VALUES (1425981465470981,1, 1, 'MASTERCARD');
INSERT INTO credit_card(id, users_id, bank_account_id, name) VALUES (8563874019583452,1, 2, 'MASTERCARD');
INSERT INTO credit_card(id, users_id, bank_account_id, name) VALUES (7634871950856123,2, 3, 'MASTERCARD');
INSERT INTO credit_card(id, users_id, bank_account_id, name) VALUES (4628950275781835,3, 4, 'MASTERCARD');
INSERT INTO credit_card(id, users_id, bank_account_id, name) VALUES (7465919573729103,4, 5, 'MASTERCARD');
INSERT INTO credit_card(id, users_id, bank_account_id, name) VALUES (7345617285612373,4, 6, 'MASTERCARD');
INSERT INTO credit_card(id, users_id, bank_account_id, name) VALUES (9817561352747683,6, 7, 'MASTERCARD');
INSERT INTO credit_card(id, users_id, bank_account_id, name) VALUES (8712656362814678,6, 8, 'MASTERCARD');
INSERT INTO credit_card(id, users_id, bank_account_id, name) VALUES (6465712485829456,7, 9, 'VISA');
INSERT INTO credit_card(id, users_id, bank_account_id, name) VALUES (7165729275782521,8, 10, 'VISA');
INSERT INTO credit_card(id, users_id, bank_account_id, name) VALUES (7588357526123456,8, 11, 'VISA');
INSERT INTO credit_card(id, users_id, bank_account_id, name) VALUES (7216123858616213,9, 12, 'VISA');
INSERT INTO credit_card(id, users_id, bank_account_id, name) VALUES (7561515831009400,10, 13, 'VISA');
INSERT INTO credit_card(id, users_id, bank_account_id, name) VALUES (7564719209847267,11, 14, 'VISA');
INSERT INTO credit_card(id, users_id, bank_account_id, name) VALUES (6561758219501850,11, 15, 'VISA');



INSERT INTO payment(users_id, sum) VALUES (1, 15.55);
INSERT INTO payment(users_id, sum) VALUES (1, 54.11);
INSERT INTO payment(users_id, sum) VALUES (1, 999.44);
INSERT INTO payment(users_id, sum) VALUES (2, 153.54);
INSERT INTO payment(users_id, sum) VALUES (2, 543.11);
INSERT INTO payment(users_id, sum) VALUES (4, 545.99);
INSERT INTO payment(users_id, sum) VALUES (4, 542.44);
INSERT INTO payment(users_id, sum) VALUES (5, 456.31);
INSERT INTO payment(users_id, sum) VALUES (6, 542.11);
INSERT INTO payment(users_id, sum) VALUES (7, 421.64);
INSERT INTO payment(users_id, sum) VALUES (7, 151.53);
INSERT INTO payment(users_id, sum) VALUES (8, 1115.33);
INSERT INTO payment(users_id, sum) VALUES (9, 1435.67);
INSERT INTO payment(users_id, sum) VALUES (10, 1569.34);
INSERT INTO payment(users_id, sum) VALUES (2, 982.54);
