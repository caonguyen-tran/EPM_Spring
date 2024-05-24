<%-- 
    Document   : index
    Created on : Apr 11, 2024, 7:06:09 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<div style="display: flex; justify-content: space-between">
    <h1>Các hoạt động đang diễn ra</h1>
    <a class="btn btn-info" style="margin: 10px 0px;" href="<c:url value="/activity"/>">Tạo mới hoạt động</a>
</div>

<table class="table table-striped mt-1">
    <tr>
        <th>Mã</th>
        <th>Tên hoạt động</th>
        <th>Ngày bắt đầu</th>
        <th>Ngày kết thúc</th>
        <th>Số lượng</th>
        <th>Mô tả</th>
        <th>Hình ảnh</th>
        <th>Điều</th>
    </tr>

    <c:forEach items="${activities}" var="a">
        <tr>
            <td>${a.id}</td>
            <td>${a.name}</td>
            <td>${a.startDate}</td>
            <td>${a.endDate}</td>
            <td>${a.slots}</td>
            <td>${a.description}</td>
            <td style="width: 240px"> <img class="card-img-top" src="${a.image}" alt="${a.name}" style="width:200px;"></td>
            <td style="width: 80px">${a.termId.name}</td>
        </tr>
    </c:forEach>
</table>