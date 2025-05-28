create database quan_ly_sinh_vien2;

use quan_ly_sinh_vien2;

create table classes(
	class_id int primary key auto_increment,
    class_name varchar(60) not null,
    start_date datetime not null,
    `status` bit
);

create table students(
	student_id int primary key auto_increment,
    student_name varchar(30) not null,
    address varchar(50),
    phone varchar(20),
    `status` bit,
    class_id int not null,
    foreign key(class_id) references classes(class_id)
);

create table subjects(
	sub_id int primary key auto_increment,
    sub_name varchar(30) not null,
    credit tinyint not null default 1, check(credit >= 1),
    `status` bit default 1
);

create table marks(
	mark_id int primary key auto_increment,
    sub_id int not null unique,
    student_id int not null unique,
    mark float default 0, check(mark > 0 and mark <= 100),
    exam_time tinyint default 1,
    foreign key(sub_id) references subjects(sub_id),
    foreign key(student_id) references students(student_id)
);