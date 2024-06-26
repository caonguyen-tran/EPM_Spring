<%-- 
    Document   : join
    Created on : May 8, 2024, 7:14:27 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h1>
    Danh sách tham gia 
</h1>

<div>
    <form action="${action}" method="get" style="display: flex; justify-content: space-between">
        <div class="form-floating" style="margin-bottom: 12px; width: 250px">
            <select class="form-select" aria-label="Default select example" name="activityId">
                <c:forEach items="${activities}" var="a">
                    <option value="${a.id}">${a.name}</option>
                </c:forEach>
            </select>
            <label for="categoryId" class="form-label">Tên hoạt động:</label>
        </div>
        <div class="form-floating" style="margin-bottom: 12px; width: 250px">
            <select class="form-select" aria-label="Default select example" name="facultyId">
                <c:forEach items="${faculties}" var="f">
                    <option value="${f.id}">${f.name}</option>
                </c:forEach>
            </select>
            <label for="categoryId" class="form-label">Khoa tổ chức:</label>
        </div>
        <div class="form-floating" style="margin-bottom: 12px; width: 250px">
            <select class="form-select" aria-label="Default select example" name="classId" disabled>
                <c:forEach items="${classes}" var="c">
                    <option value="${c.id}">${c.name}</option>
                </c:forEach>
            </select>
            <label for="categoryId" class="form-label">Lớp:</label>
        </div>
        <div class="form-floating" style="margin-bottom: 12px; width: 250px">
            <select class="form-select" aria-label="Default select example" name="semesterId" disabled>
                <c:forEach items="${semesters}" var="s">
                    <option value="${s.id}"> ${s.description} </option>
                </c:forEach>
            </select>
            <label for="categoryId" class="form-label">Kì:</label>
        </div>
        <button type="submit" class="btn btn-primary" style="height: 50px; width: 70px">Tìm</button>
    </form>
</div>

<div style="min-height: 700px">
    <table class="table table-striped mt-1">
        <tr>
            <th>Mã</th>
            <th>Tên hoạt động</th>
            <th>Điều</th>
            <th>Tên sinh viên</th>
            <th>Lớp</th>
            <th>Khoa</th>
            <th>Ngày tham gia</th>
            <th>Bằng chứng</th>
            <th>Ghi chú</th>
            <th>Xác nhận</th>
        </tr>

        <c:forEach items="${joins}" var="j">
            <tr>
                <td>${j[0].id}</td>
                <td>${j[2].name}</td>
                <td>${j[5].name}</td>
                <td>${j[4].lastname} ${j[4].firstname}</td>
                <td>${j[6].name}</td>
                <td>${j[7].name}</td>
                <td>${j[0].dateRegister}</td>
                <td style="width: 150px"><img class="card-img-top" src="${j[0].proofJoining}" style="width:200px;"></td>
                <td>${j[0].note}</td>
                <td>
                    <div>
                        <c:url value="/api/score-student/accept" var="url1"/>
                        <button onclick="createScoreStudent('${url1}', ${j[0].id}, ${j[2].id})" class="btn btn-primary" type="button" style="width: 100px; margin-bottom: 5px">Xác nhận</button>
                        <c:url value="/api/join-activity/${j[0].id}" var="url2"/>
                        <button onclick="deleteJoined('${url2}', '${j[0].id}')" class="btn btn-danger" type="button" style="width: 100px">Từ chối</button>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>


<script src="<c:url value="/js/script.js" />"></script>