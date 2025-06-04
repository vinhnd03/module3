<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chi tiết sản phẩm</title>
</head>
<body>
    <h1>Tên sản phẩm: ${product.name}</h1>
    <h2>Giá: ${product.price}</h2>
    <h2>Mô tả: ${product.description}</h2>
    <h2>Trạng thái: ${product.status ? "Còn hàng" : "Hết hàng"}</h2>
    <button onclick="window.location.href='/product'">Danh sách</button>
</body>
</html>
