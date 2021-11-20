CREATE TABLE user(
    id bigint not null auto_increment,
    name varchar(50) not null,
    email varchar(50) not null,
    primary key(id)
);

INSERT INTO user VALUES(1, 'Luiz Reginaldo', 'contato@luizreginaldo.com');