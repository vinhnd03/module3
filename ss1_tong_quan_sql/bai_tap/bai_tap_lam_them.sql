create database codegym_management;

use codegym_management;

create table phong_hoc(
	id int primary key auto_increment,
    ten varchar(255)
);

create table giang_vien(
	id int primary key auto_increment,
	ten varchar(255),
	ngay_sinh date,
    luong bigint
);

create table hoc_vien(
	id int primary key auto_increment,
    ten varchar(255),
    gioi_tinh bit,
    email varchar(255),
    diem float,
    lop_hoc_id int
);

create table tai_khoan(
	id int primary key auto_increment,
    ten_dang_nhap varchar(255),
    matKhau varchar(255),
    hoc_vien_id int unique,
    foreign key(hoc_vien_id) references hoc_vien(id)
);

create table lop_hoc(
	id int primary key auto_increment,
	ten varchar(255),
    giang_vien_id int unique,
    foreign key(giang_vien_id) references giang_vien(id)
);

alter table hoc_vien add foreign key(lop_hoc_id) references lop_hoc(id);

create table phan_chia_lop_hoc(
	id int primary key auto_increment,
    lop_hoc_id int,
    phong_hoc_id int,
    foreign key(lop_hoc_id) references lop_hoc(id),
    foreign key(phong_hoc_id) references phong_hoc(id)
);