-- 创建数据库
create database easymall;

use easymall;

create table user (
    id int primary key auto_increment,
    username varchar(100),
    password varchar(100),
    nickname varchar(100),
    email varchar(100)
);

insert into user values(null, 'admin', 'admin', '管理员', 'admin@sunhao.pro');
insert into user values(null, 'haha', 'admin', 'hehe', '123@qq.com');