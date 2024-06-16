<%-- 
    Document   : missing_report
    Created on : Apr 24, 2024, 8:24:14 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>
    Danh sách báo thiếu
</h1>
<c:url value="/report" var="action" />
<form action="${action}" method="get">
    <div class="form-floating" style="margin-bottom: 12px; width: 250px">
        <select class="form-select" aria-label="Default select example" name="facultyId">
            <c:forEach items="${faculties}" var="f">
                <option value="${f.id}"> ${f.name}</option>
            </c:forEach>
        </select>
        <label for="categoryId" class="form-label">Khoa tổ chức:</label>
    </div>
    <button type="submit" class="btn btn-primary">Tìm</button>
</form>

<div style="min-height: 700px">
    <table class="table table-striped mt-1">
        <tr>
            <th>Mã</th>
            <th>Tên hoạt động</th>
            <th>Điều</th>
            <th>Tên sinh viên</th>
            <th>Khoa</th>
            <th>Ngày tạo báo thiếu</th>
            <th>Bằng chứng</th>
            <th>Xác nhận</th>
        </tr>

        <c:forEach items="${reports}" var="r">
            <tr>
                <td>${r[0].id}</td>
                <td>${r[1].name}</td>
                <td>${r[3].id}</td>
                <td>${r[4].lastname} ${r[4].firstname}</td>
                <td>${r[6].name}</td>
                <td>${r[0].createdDate}</td>
                <td style="width: 150px"><img class="card-img-top" src="${r[0].proofJoining}" style="width:200px;"></td>
                <td>
                    <c:url value="/" var="url" />
                    <a class="btn btn-info" href="<c:url value="" />">Xác nhận</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>