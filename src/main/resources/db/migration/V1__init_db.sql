create table usr
(
    id       bigint       not null auto_increment,
    username     varchar(255) not null,
    password varchar(255) not null,
    age      int          not null,
    status   varchar(100) default 'ACTIVE',
    created  TIMESTAMP    default current_timestamp(),
    updated  TIMESTAMP    default current_timestamp(),
    primary key (id)
);

create table article
(
    id      bigint not null auto_increment,
    text    varchar(255),
    color   varchar(255),
    primary key (id),
    user_id int,
    foreign key (user_id) references usr (id),
    status  varchar(100) default 'ACTIVE',
    created TIMESTAMP    default current_timestamp(),
    updated TIMESTAMP    default current_timestamp()
);

create table role
(
    id      bigint not null auto_increment,
    role    varchar(100),
    status  varchar(100) default 'ACTIVE',
    created TIMESTAMP    default current_timestamp(),
    updated TIMESTAMP    default current_timestamp()
);

create table user_role (
    user_id bigint,
    role_id bigint,
    foreign key (user_id) references usr (id),
    foreign key (role_id) references role(id)
)