<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="get" action="/product">
    <input type="hidden" name="action" value="search">
    <label for="searchName">Nhập tên: </label>
    <input type="text" name="searchName" id="searchName">
    <input type="submit" value="Tìm kiếm">
</form>
<hr>
<table border="1">
    <tr>
        <th>#</th>
        <th>Tên sản phẩm</th>
        <th>Giá</th>
        <th>Loại</th>
        <th>Trạng thái</th>
        <th>Thao tác</th>
    </tr>
    <c:forEach var="item" items="${productDtos}" varStatus="index">
        <tr>
            <td>${item.productId}</td>
            <td>${item.productName}</td>
            <td>${item.price}</td>
            <td>${item.categoryName}</td>
            <td>${item.status ? "Còn hàng" : "Hết hàng"}</td>
            <td>
                <button type="button" onclick="window.location.href='/product?action=edit&id=${item.productId}'">Sửa</button>
                <form method="post" style="display: inline">
                    <button formaction="/product?action=delete&id=${item.productId}">Xóa</button>
                    <button formaction="/product?action=detail&id=${item.productId}">Xem chi tiết</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<button type="button" onclick="window.location.href='/product?action=add'">Thêm</button>
</body>
</html>
