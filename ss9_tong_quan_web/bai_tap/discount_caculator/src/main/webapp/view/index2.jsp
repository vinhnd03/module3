<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/display-discount" method="post">
    <label>Mô tả</label>
    <input type="text" name="description"><br>
    <label>Giá</label>
    <input type="number" name="price"><br>
    <label>Tỉ lệ chiết khấu</label>
    <input type="number" name="percent"><br>
    <button type="submit">Tính</button>
</form>
<c:if test="${newPrice != null}">
    <p>Lượng chiết khấu: ${discountAmount}</p>
    <p>Giá sau khi đã được chiết khấu: ${newPrice}</p>
</c:if>
</body>
</html>
