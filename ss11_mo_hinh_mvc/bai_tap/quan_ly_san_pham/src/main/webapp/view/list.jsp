<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<form method="get" action="/product">
    <input type="hidden" name="action" value="search">
    <label for="searchName">Nhập tên: </label>
    <input type="text" name="searchName" id="searchName" value="${searchName}">
    <select name="categoryId" id="categoryId">
        <option value="0">Tất cả</option>
        <c:forEach var="item" items="${categories}">
            <option value="${item.id}" <c:if test="${categoryId == item.id}">selected</c:if>>${item.name}</option>
        </c:forEach>
    </select>
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
    <c:forEach var="item" items="${products}" varStatus="index">
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.price}</td>
            <td>${item.categoryId}</td>
            <td>${item.status ? "Còn hàng" : "Hết hàng"}</td>
            <td>
                <button type="button" onclick="window.location.href='/product?action=edit&id=${item.id}'">Sửa</button>
                <form method="post" style="display: inline">
<%--                    <button formaction="/product?action=delete&id=${item.id}">Xóa</button>--%>
                    <button type="button" onclick="deleteInfo('${item.id}', '${item.name}')"
                            data-bs-toggle="modal" data-bs-target="#exampleModal">Xóa</button>
                    <button formaction="/product?action=detail&id=${item.id}">Xem chi tiết</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<button type="button" onclick="window.location.href='/product?action=add'">Thêm</button>
<form method="get" style="display: inline">
    <%--    <input type="hidden" name="action" value="list" />--%>
    <input type="hidden" name="page" value="1"/>
    <button <c:if test="${currentPage == 1}">disabled</c:if>>|&lt;</button>
</form>
<form method="get" style="display: inline">
    <input type="hidden" name="page" value="${currentPage - 1}">
    <button <c:if test="${currentPage == 1}">disabled</c:if>>&lt;&lt;</button>
</form>
<span>Trang ${currentPage} / ${totalPage}</span>
<form method="get" style="display: inline">
    <input type="hidden" name="page" value="${currentPage + 1}">
    <button <c:if test="${currentPage == totalPage}">disabled</c:if>>&gt;&gt;</button>
</form>
<form method="get" style="display: inline">
    <input type="hidden" name="page" value="${totalPage}"/>
    <button <c:if test="${currentPage == totalPage}">disabled</c:if>>&gt;|</button>
</form>

<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form method="post" action="/product?action=delete">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <input hidden="hidden" id="deleteId" name="deleteId">
                    <span>Bạn có muốn xoá sản phẩm </span><span id="deleteName"></span> không?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Huỷ</button>
                    <button  class="btn btn-primary">Xoá</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script>
    function deleteInfo(id, name) {
        document.getElementById("deleteId").value= id;
        document.getElementById("deleteName").innerText= name;
    }
</script>

<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<!-- Option 2: Separate Popper and Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
