create database if not exists c0225g1;
use c0225g1;
create table jame(
username varchar(50) primary key,
`password`  varchar(50)
);
create table class(
id int primary key auto_increment,
name varchar(50)
);
create table room(
id int primary key auto_increment,
name varchar(50),
class_id int,
foreign key(class_id) references class(id)
);
create table student(
id int primary key auto_increment,
name varchar(50),
gender boolean,
birthday date,
email varchar(100),
point float,
username varchar(50) unique,
class_id int,
foreign key(username) references jame(username),
foreign key(class_id) references class(id)
);
create table instructor(
id int primary key auto_increment,
name varchar(50),
birthday date,
salary float
);
create table instructor_class(
 instructor_id int,
 class_id int,
 start_time date,
 end_time_time date,
 primary key (instructor_id,class_id),
 foreign key(instructor_id) references instructor(id),
 foreign key(class_id) references class(id)
);

INSERT INTO jame (username, password) VALUES
('an123', 'pass123'),
('binh456', '123456'),
('hoa789', 'hoa@789'),
('minh001', 'minhpass'),
('linh002', 'abcxyz');

INSERT INTO class (name) VALUES
('Lập trình Java'),
('Phát triển Web'),
('Phân tích dữ liệu');

INSERT INTO room (name, class_id) VALUES
('Phòng A1', 1),
('Phòng B1', 1),
('Phòng A2', 2),
('Phòng C1', 3);

INSERT INTO student (name, gender, birthday, email, point, username, class_id) VALUES
('Nguyễn Văn An', 1, '2002-05-01', 'an@gmail.com', 8.5, 'an123', 1),
('Trần Thị Bình', 0, '2001-11-21', 'binh@gmail.com', 7.8, 'binh456', 2),
('Lê Thị Hoa', 0, '2003-02-18', 'hoa@gmail.com', 9.0, 'hoa789', 1),
('Phạm Minh', 1, '2002-08-10', 'minh@gmail.com', 6.9, 'minh001', 3),
('Đặng Linh', 0, '2000-12-25', 'linh@gmail.com', 8.2, 'linh002', 2);

INSERT INTO instructor (name, birthday, salary) VALUES
('Thầy Nam', '1980-03-12', 15000000),
('Cô Hồng', '1985-07-08', 16000000),
('Thầy Phong', '1978-09-20', 17000000);

INSERT INTO instructor_class (instructor_id, class_id, start_time, end_time_time) VALUES
(1, 1, '2024-01-01', '2024-06-01'),
(2, 2, '2024-02-01', '2024-07-01'),
(3, 3, '2024-03-01', '2024-08-01'),
(1, 2, '2024-04-01', '2024-09-01');

-- 1. Lấy ra thông tin các học viên, và cho biết các học viên đang theo học lớp nào.
select A.id, A.name, B.name from student A inner join class B on A.class_id = B.id;

-- 2. Lấy ra thông tin các học viên, và cho biết các học viên đang theo học lớp nào và cả các bạn đã đăng ký nhưng chưa có lớp học.
select A.*, B.name from student A left join class B on A.class_id = B.id;

-- 4. Lấy thông tin của các học viên tên “Hai” và 'Huynh’.
select * from student where name like '%huynh%' or name like '%hai';

-- 5. Lấy ra học viên có điểm lớn hơn 5 .
select * from student where point > 5;

-- 6. Lấy ra học viên có họ là “nguyen”
select * from student where name like 'nguyen%';

-- 7. Thông kế số lượng học sinh theo từng loại điểm.
select point, COUNT(*) AS so_luong from student group by point order by point;

-- 8 . Thông kế số lượng học sinh theo điểm và điểm phải lớn hơn 5
select COUNT(*) AS so_luong_hoc_sinh_co_diem_lon_hon_5 from student where point > 5;

-- 9. Thông kế số lượng học sinh theo điểm lớn hơn 5 và chỉ hiện thị với số lượng>=2
select point,COUNT(*) AS so_luong from student where point > 5 group by point having COUNT(*) >= 2;

-- 10. Lấy ra danh sách học viên của lớp c1121g1 và sắp xếp tên học viên theo alphabet.
select A.* from student A inner join class B on a.class_id = b.id where b.name = 'Lập trình Java' order by A.name asc;


-- -----------------------------------------------------------------------------------------------------------

-- 1. Hiện thị danh sách các lớp có học viên theo học và số lượng học viên của mỗi lớp
select c.name, (select count(s.id) from student s where s.class_id = c.id) as so_luong from class c;

-- 2. Tính điểm lớn nhất của mỗi các lớp
select c.name, (select max(s.point) from student s where s.class_id = c.id) as diem_cao_nhat from class c;

-- 3. Tình điểm trung bình của từng lớp
select c.name, (select avg(s.point) from student s where s.class_id = c.id) as diem_cao_nhat from class c;

-- 4. Lấy ra toàn bộ tên và ngày sinh các instructor và student ở CodeGym.
select s.name from student s
union
select i.name from instructor i;

-- 5. Lấy ra top 3 học viên có điểm cao nhất của trung tâm.
select s.name, s.point from student s
order by s.point desc
limit 3;

-- 6. Lấy ra các học viên có điểm số là cao nhất của trung tâm.
select s.name, s.point from student s
where s.point = (
	select max(point) from student
);