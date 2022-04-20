create table roles
(
    id bigSerial PRIMARY KEY,
    name varchar (50) not null
);

create table users
(
    id bigSerial primary key,
    login varchar(50) not null,
    password varchar(500),
    roleId bigSerial not null,
    foreign key (roleId) references roles (id)
);

CREATE TABLE clients
(
    id bigserial PRIMARY KEY,
    name varchar(255) not null,
    type int not null,
    userId bigSerial,
    foreign key (userId) references users (id)
);

CREATE TABLE banks
(
    id bigserial PRIMARY KEY,
    name varchar(255) not null UNIQUE
);

CREATE TABLE bankAccounts
(
    id bigserial PRIMARY KEY,
    number int not null unique,
    currency varchar(255) not null,
    amountOfMoney real,
    bankId int not null,
    foreign key (bankId) references banks (id),
    clientId int not null,
    foreign key (clientId) references clients (id)
);

create table transactions
(
    id bigserial PRIMARY KEY,
    senderId int not null,
    receiverId int not null,
    amountOfMoney real not null,
    senderAccountId int not null,
    receiverAccountId int not null,
    date timestamp not null,
    foreign key (senderId) references clients (id),
    foreign key (receiverId) references clients (id),
    foreign key (senderAccountId) references bankAccounts (id),
    foreign key (receiverAccountId) references bankAccounts (id)
);

create table commissionForClients
(
    id bigSerial primary key,
    bankId bigserial not null,
    clientType int not null,
    commission real not null,
    foreign key (bankId) references banks (id)
);

create table exchangeRates
(
    id bigSerial PRIMARY KEY,
    currency int not null UNIQUE,
    rate real not null
);

