<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Tên</th>
        <th>Ngày sinh</th>
        <th>Địa chỉ</th>
    </tr>
    <c:forEach var="item" items="${customers}">
        <tr>
            <td>${item.name}</td>
            <td>${item.birthday}</td>
            <td>${item.address}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
