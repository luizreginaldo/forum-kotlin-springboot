CREATE TABLE course(
    id bigint not null,
    name varchar(50) not null,
    category varchar(50) not null,
    primary key(id)
);

INSERT INTO course(id, name, category) VALUES (1, 'Kotlin', 'Programming')