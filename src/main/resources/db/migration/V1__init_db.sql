create table usr
(
    id   bigint         not null auto_increment,
    name varchar(255) not null,
    password varchar(255) not null,
    age  int          not null,
    status varchar(100) default 'ACTIVE',
    primary key (id)
);

create table article
(
    id    bigint not null auto_increment,
    text  varchar(255),
    color varchar(255),
    primary key (id),
    user_id int,
    foreign key (user_id) references usr(id)
);

create table role
(
    id bigint not null auto_increment,
    role varchar(100),
    user_id int,
    foreign key (user_id) references usr(id)
)