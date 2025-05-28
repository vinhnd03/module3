create database quan_ly_sinh_vien3;

use quan_ly_sinh_vien3;

create table classes(
	id int auto_increment primary key,
    name varchar(20),
    start_date date,
    status boolean
);

create table students(
	id int auto_increment primary key,
    name varchar(50),
    address varchar(50),
    phone varchar(11),
    status boolean,
    class_id int,
    foreign key(class_id) references classes(id)
);

create table subjects(
	id int auto_increment primary key,
    name varchar(20),
    credit int,
    status boolean
);

create table marks(
	id int auto_increment primary key,
    subject_id int,
    student_id int,
    mark int,
    exam_times int,
    foreign key(subject_id) references subjects(id),
    foreign key(student_id) references students(id)
);

INSERT INTO classes
VALUES 
(1, 'A1', '2008-12-20', 1),
(2, 'A2', '2008-12-22', 1),
(3, 'B3', current_date, 0);

INSERT INTO students (name, address, phone, status, class_id)
VALUES 
('Hung', 'Ha Noi', '0912113113', 1, 1),
('Hoa', 'Hai phong',NULL, 1, 1),
('Manh', 'HCM', '0123123123', 0, 2);

INSERT INTO subjects (name, credit, status)
VALUES 
('CF', 5, 1),
 ('C', 6, 1),
 ('HDJ', 5, 1),
 ('RDBMS', 10, 1);
 
 INSERT INTO marks (subject_id, student_id, mark, exam_times)
VALUES (1, 1, 8, 1),
 (1, 2, 10, 2),
 (2, 1, 12, 1);


select s.id, s.name, c.name from students s join classes c on s.class_id = c.id
where c.name = 'A1';

select s.id, s.name, sub.name, m.mark from students s join marks m on s.id = m.student_id join subjects sub on m.subject_id 
where sub.name = 'CF';

-- Hiển thị tất cả các sinh viên có tên bắt đầu bảng ký tự ‘h’
select * from students where name like 'h%';

-- Hiển thị các thông tin lớp học có thời gian bắt đầu vào tháng 12.
select * from classes where month(start_date) = 12;

-- Hiển thị tất cả các thông tin môn học có credit trong khoảng từ 3-5.
select * from subjects where credit >= 3 and credit <= 5;

-- Thay đổi mã lớp(ClassID) của sinh viên có tên ‘Hung’ là 2.
update students set class_id = 2 where name = 'Hung';

-- Hiển thị các thông tin: StudentName, SubName, Mark. Dữ liệu sắp xếp theo điểm thi (mark) giảm dần. nếu trùng sắp theo tên tăng dần.
select s.id, s.name, sub.name, m.mark from students s join marks m on s.id = m.student_id join subjects sub on m.subject_id
group by s.name, sub.name
order by m.mark desc, s.name asc;

-- -----------------------------------------------------------------------------------------------------------------------------------------
-- Hiển thị tất cả các thông tin môn học (bảng subject) có credit lớn nhất.
select * from subjects s
where s.credit = (
	select max(s2.credit) from subjects s2
);

-- Hiển thị các thông tin môn học có điểm thi lớn nhất.
select s.* from subjects s
where s.id in (
	select m.subject_id from marks m 
    where m.mark = (select max(mark) from marks )
);

-- Hiển thị các thông tin sinh viên và điểm trung bình của mỗi sinh viên, xếp hạng theo thứ tự điểm giảm dần
select s.*, avg(m.mark) as diem_trung_binh from students s 
left join marks m on s.id = m.student_id
group by s.id
order by diem_trung_binh desc