create database if not exists quan_ly_ban_hang;

use quan_ly_ban_hang;

create table customers(
	id int primary key auto_increment,
    `name` varchar(50),
    age int
);

create table orders(
	id int primary key auto_increment,
    customer_id int,
    date date,
    total_price bigint,
    foreign key(customer_id) references customers(id)
);

create table products(
	id int primary key auto_increment,
    `name` varchar(50),
    price bigint
);

create table order_details(
	order_id int,
    product_id int,
    quantity int,
    primary key(order_id, product_id),
    foreign key(order_id) references orders(id),
    foreign key(product_id) references products(id)
);