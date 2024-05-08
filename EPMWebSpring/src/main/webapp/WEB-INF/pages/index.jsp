<%-- 
    Document   : index
    Created on : Apr 11, 2024, 7:06:09 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1>Các hoạt động đang diễn ra</h1>


<table class="table table-striped mt-1">
    <tr>
        <th>Mã</th>
        <th>Tên hoạt động</th>
        <th>Ngày bắt đầu</th>
        <th>Ngày kết thúc</th>
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
            <td>${a.description}</td>
            <td style="width: 240px"> <img class="card-img-top" src="${a.image}" alt="${a.name}" style="width:200px;"></td>
            <td style="width: 80px">${a.termId.name}</td>
        </tr>
    </c:forEach>
</table>