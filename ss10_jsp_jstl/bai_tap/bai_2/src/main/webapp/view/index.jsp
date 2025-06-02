<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/calculator" method="post">
    <fieldset>
        <legend>Calculator</legend>

        <label for="first">First operand:</label>
        <input type="text" id="first" name="first"><br><br>

        <label for="operator">Operator:</label>
        <select id="operator" name="operator">
            <option value="add">Addition</option>
            <option value="sub">Subtraction</option>
            <option value="mul">Multiplication</option>
            <option value="div">Division</option>
        </select><br><br>

        <label for="second">Second operand:</label>
        <input type="text" id="second" name="second"><br><br>

        <button type="submit">Calculate</button>

        <c:if test="${message != ''}">
            <p>Lỗi: ${message}</p>
        </c:if>
        <c:if test="${message == ''}">
            <p>Kết quả: ${result}</p>
        </c:if>
    </fieldset>
</form>
</body>
</html>
