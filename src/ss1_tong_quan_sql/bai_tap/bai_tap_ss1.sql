create database quan_ly_sinh_vien;

use quan_ly_sinh_vien;

create table classes(
	id int primary key auto_increment,
    name varchar(255)
);

create table teachers(
	id int primary key auto_increment,
    name varchar(255),
    age int,
    country varchar(255)
);

insert into classes (name)
values ("12A1"), ("12A2"), ("12A3");

select * from classes;

insert into teachers (name, age, country)
values
("Nguyen Van A", 30, "Vietnam"),
("John Cena", 42, "USA");

select * from teachers;