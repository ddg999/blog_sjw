create table board_tb(
    id int auto_increment primary key,
    author varchar(60) not null,
    title varchar(60) not null,
    content varchar(60) not null,
    post_number int auto_increment,
    created_at timestamp not null default now()
);