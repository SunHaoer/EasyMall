-- 创建数据库 --
create database easymall;

use easymall;

-- 用户表 --
create table user (
    id int primary key auto_increment,
    username varchar(100),
    password varchar(100),
    nickname varchar(100),
    email varchar(100)
);

insert into user values(null, 'admin', 'admin', '管理员', 'admin@sunhao.pro');
insert into user values(null, 'haha', 'admin', 'hehe', '123@qq.com');

-- 商品种类表 --
create table prod_category (
	id int primary key auto_increment,
	cname varchar(100)
);

-- 商品表 --
create table prod (
	id int primary key auto_increment,
	pname varchar(100),
	price double,
	cid int, 	-- 商品种类的id
	pnum int, 	-- 商品的数量
	imgurl varchar(200),		-- 商品图片的url
	description varchar(200)	-- 商品描述
);

-- 创建购物车表 --
create table (user.name + _car) (
	id int primary key auto_increment,
	pid int,
	pname varchar(100)
); 


create table (user.name + _car) (id int primary key auto_increment, pid int, pname varchar(100)); 




