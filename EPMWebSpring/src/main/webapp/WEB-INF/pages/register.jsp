<%-- 
    Document   : register
    Created on : May 8, 2024, 7:52:13 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h1>
    Danh sách đăng ký
</h1>

<div style="min-height: 700px">
    <table class="table table-striped mt-1">
        <tr>
            <th>Mã</th>
            <th>Tên hoạt động</th>
            <th>Điều</th>
            <th>Tên sinh viên</th>
            <th>Khoa</th>
            <th>Ngày đăng ký</th>
        </tr>

        <c:forEach items="${registers}" var="r">
            <tr>
                <td>${r[0].id}</td>
                <td>${r[2].name}</td>
                <td>${r[4].name}</td>
                <td>${r[3].lastname} ${r[3].firstname}</td>
                <td>${r[6].name}</td>
                <td>${r[0].dateRegister}</td>
            </tr>
        </c:forEach>
    </table>
</div>