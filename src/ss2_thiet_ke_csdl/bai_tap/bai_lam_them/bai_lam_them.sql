create database quan_ly_quan_net;

use quan_ly_quan_net;

create table loai_khach_hang (
	id int primary key auto_increment,
    ten varchar(50)
);

create table hang_san_xuat (
	id int primary key auto_increment,
    ten varchar(50)
);

create table dich_vu_di_kem (
	id int primary key auto_increment,
	ten varchar(50),
    gia bigint
);

create table khach_hang (
	id int primary key auto_increment,
    ten varchar(50),
    sdt varchar(11),
    email varchar(50),
    id_loai_kh int,
    foreign key(id_loai_kh) references loai_khach_hang(id)
);

create table may_tinh (
	id int primary key auto_increment,
    id_hang_sx int,
    vi_tri varchar(50),
    foreign key(id_hang_sx) references hang_san_xuat(id)
);

create table su_dung_dich_vu (
	id int primary key auto_increment,
    id_may int,
    id_kh int,
    bat_dau datetime,
    ket_thuc datetime,
    foreign key(id_may) references may_tinh(id),
    foreign key(id_kh) references khach_hang(id)
);

create table chi_tiet_su_dung_dich_vu (
	id_dich_vu int,
    id_su_dung_dv int,
    primary key(id_dich_vu, id_su_dung_dv),
    foreign key(id_dich_vu) references dich_vu_di_kem(id),
    foreign key(id_su_dung_dv) references su_dung_dich_vu(id)
);





