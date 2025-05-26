create database demo_product;

use demo_product;

create table Products (
	id int primary key auto_increment,
    productCode varchar(50) not null,
    productName varchar(100),
    productPrice decimal(10,2),
    productAmount int,
    productDescription varchar(255),
    productStatus varchar(20)
);

insert into Products (productCode, productName, productPrice, productAmount, productDescription, productStatus) values
('P001', 'Keyboard', 150.00, 20, 'Mechanical keyboard', 'Available'),
('P002', 'Mouse', 50.00, 50, 'Wireless mouse', 'Available'),
('P003', 'Monitor', 300.00, 15, '24 inch HD monitor', 'Out of Stock'),
('P004', 'Laptop', 1200.00, 10, 'Gaming laptop', 'Available'),
('P005', 'Webcam', 80.00, 30, 'HD webcam', 'Available');

-- Tạo Unique Index trên bảng Products (sử dụng cột productCode để tạo chỉ mục)
	create unique index p_index on Products(productCode);
    -- drop index p_index on Products;

-- Tạo Composite Index trên bảng Products (sử dụng 2 cột productName và productPrice)
	create index p_index2 on Products(productName, productPrice);

-- Sử dụng câu lệnh EXPLAIN để biết được câu lệnh SQL của bạn thực thi như nào
	explain select productCode from Products;
    explain select productName from Products;

-- So sánh câu truy vấn trước và sau khi tạo index
	explain select productAmount from Products;
    
--  Tạo view lấy về các thông tin: productCode, productName, productPrice, productStatus từ bảng products.
	create view p_view as (
		select productCode, productName, productPrice, productStatus from Products
    );
	select * from p_view;
    
-- Tiến hành sửa đổi view
	alter view p_view as (
		select productCode, productName, productPrice from Products
    );

-- Tiến hành xoá view
	drop view p_view;
    
-- Tạo store procedure lấy tất cả thông tin của tất cả các sản phẩm trong bảng product
	Delimiter //
    create procedure product_sp ()
    begin
		select * from Products;
    end//
    Delimiter ;
    
    call product_sp();

-- Tạo store procedure thêm một sản phẩm mới
Delimiter //
    create procedure create_product_sp (
		    p_code varchar(50),
			p_name varchar(100),
			p_price decimal(10,2),
			p_amount int,
			p_description varchar(255),
			p_status varchar(20)
    )
    begin
		insert into Products (productCode, productName, productPrice, productAmount, productDescription, productStatus) values
        (p_code, p_name, p_price, p_amount, p_description, p_status);
    end//
    Delimiter ;

-- Tạo store procedure sửa thông tin sản phẩm theo id
	Delimiter //
    create procedure update_product_by_id_sp (IN
			id_product int,
            p_code varchar(50),
			p_name varchar(100),
			p_price decimal(10,2),
			p_amount int,
			p_description varchar(255),
			p_status varchar(20)
    )
    begin
		update Products set productCode = p_code,
        productName = p_name, 
        productPrice = p_price, 
        productAmount = p_amount,
        productDescription = p_description, 
        productStatus = p_status where id = id_product;
    end//
    Delimiter ;

-- Tạo store procedure xoá sản phẩm theo id
Delimiter //
    create procedure delete_product_by_id_sp (IN
			id_product int
    )
    begin
		delete from Products where id = id_product;
    end//
    Delimiter ;
    
    call create_product_sp('P006', 'Webcam', 80.00, 30, 'HD webcam', 'Available');
    call update_product_by_id_sp(7, 'P099', 'Webcam', 80.00, 30, 'HD webcam', 'Available');
    call delete_product_by_id_sp(7);
    select * from products;