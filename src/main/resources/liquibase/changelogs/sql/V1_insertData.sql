INSERT INTO banks(name)
VALUES ('Alfa');

INSERT INTO banks(name)
VALUES ('Belarus');


-- INSERT INTO bankAccounts(number, currency, amountOfMoney, bankId, clientId)
-- VALUES (23132, 0, 500, 1, 1);
--
-- INSERT INTO bankAccounts(number, currency, amountOfMoney, bankId, clientId)
-- VALUES (45656, 1, 700, 2, 2);


INSERT INTO commissionForClients(bankId, clientType, commission)
VALUES (1, 0, 0.01);

INSERT INTO commissionForClients(bankId, clientType, commission)
VALUES (1, 1, 0.02);

INSERT INTO commissionForClients(bankId, clientType, commission)
VALUES (2, 0, 0.015);

INSERT INTO commissionForClients(bankId, clientType, commission)
VALUES (2, 1, 0.025);


INSERT INTO exchangeRates(currency, rate)
VALUES (0, 2.59);

INSERT INTO exchangeRates(currency, rate)
VALUES (1, 2.9);

INSERT INTO exchangeRates(currency, rate)
VALUES (2, 1);

INSERT INTO roles(name)
VALUES ('ROLE_ADMIN');

INSERT INTO roles(name)
VALUES ('ROLE_CLIENT');

INSERT INTO roles(name)
VALUES ('ROLE_OPERATOR');

INSERT INTO users(login, password, roleId)
VALUES ('admin', '$2a$10$HWT5OgpemJUSD3KAzNTFTOz9AiS/QYg4BRJZowyEAMlTpIXqvkjMq', 1);
