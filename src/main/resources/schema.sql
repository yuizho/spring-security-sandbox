create table user
(
    id INT auto_increment not null,
    name varchar(30) not null unique,
    password varchar(30) not null,
    authority varchar(30) not null,
    PRIMARY KEY (id)
);

insert into user(name, password, authority) values ('yuizho', 'pass', 'USER');
insert into user(name, password, authority) values ('admin', 'pass', 'ADMIN');