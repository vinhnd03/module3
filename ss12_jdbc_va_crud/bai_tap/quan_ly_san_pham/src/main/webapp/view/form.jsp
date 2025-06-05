<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
  <label for="name">Nhập tên: </label>
  <input type="text" name="name" id="name" value="${product.name}"><br>
  <label for="price">Nhập giá: </label>
  <input type="number" name="price" id="price" value="${product.price}"><br>
  <label for="categoryId">Nhập loại: </label>
  <select name="categoryId" id="categoryId">
    <option disabled ${product.categoryId == null ? 'selected' : ''}>-- Chọn loại --</option>
    <c:forEach var="item" items="${categories}">
      <option value="${item.id}"
              <c:if test="${item.id == product.categoryId}">selected</c:if>
      >${item.name}</option>
    </c:forEach>
  </select><br>
  <label>Trạng thái: </label> <br>
  <input type="radio" name="status" value="true"  <c:if test="${product.status == true || product.status == null}">checked</c:if> > Còn hàng &emsp;
  <input type="radio" name="status" value="false" <c:if test="${product.status == false}">checked</c:if> > Hết hàng <br>
  <input type="submit" value="add">
  <input type="submit" value="update">
  <input type="reset" value="reset">
  <button type="button" onclick="window.location.href='/product'">Danh sách</button>
</form>
</body>
</html>
