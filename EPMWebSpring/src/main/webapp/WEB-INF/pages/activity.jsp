<%-- 
    Document   : register
    Created on : Apr 24, 2024, 8:05:01 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1>
    Tạo 1 hoạt động
</h1>
<c:url value="/activity" var="action" />
<form method="post" action="${action}" enctype="multipart/form-data">

    <div class="form-floating mb-3 mt-3">
        <input class="form-control"  id="name"  placeholder="Tên hoạt động" name="name"/>
        <label for="name">Tên hoạt động</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <input type="datetime-local" class="form-control" value="2024-05-01T08:30" id="startDate"  placeholder="Ngày bắt đầu" name="startDate"/>
        <label for="startDate">Ngày bắt đầu</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <input type="datetime-local" class="form-control" value="2024-05-01T08:30" id="endDate"  placeholder="Ngày kết thúc" name="endDate"/>
        <label for="endDate">Ngày kết thúc</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <textarea class="form-control"  id="description"  placeholder="Tên hoạt động" style="height: 150px" name="description"></textarea>
        <label for="description">Mô tả hoạt động</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <input class="form-control"  id="slots" type="number" placeholder="Số lượng tham gia" name="slots"/>
        <label for="slots">Số lượng tham gia</label>
    </div>
    <div class="form-floating" style="margin-bottom: 12px">
        <select class="form-select" id="semesterId" name="semesterId">
            <c:forEach items="${semesters}" var="s">
                <option value="${s.id}">${s.description}</option>
            </c:forEach>
        </select>
        <label for="semesterId" class="form-label">Kì Học:</label>
    </div>
    <div class="form-floating" style="margin-bottom: 12px">
        <select class="form-select" aria-label="Default select example" name="termId">
            <c:forEach items="${terms}" var="t">
                <option value="${t.id}">${t.name} - ${t.description}</option>
            </c:forEach>
        </select>
        <label for="categoryId" class="form-label">Điều:</label>
    </div>
    <div class="form-floating" style="margin-bottom: 12px">
        <select class="form-select" aria-label="Default select example" name="facultyId">
            <c:forEach items="${faculties}" var="f">
                <option value="${f.id}">${f.name}</option>
            </c:forEach>
        </select>
        <label for="categoryId" class="form-label">Khoa tổ chức:</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <input type="file" class="form-control"  id="image" name="file"/>
        <label for="image">Ảnh của hoạt động</label>

        <c:if test="${activity.id > 0}">
            <img src="${activity.image}" width="200" class="img-fluid" />
        </c:if>
    </div>
    <div class="form-floating">
        <button class="btn btn-info mt-1" type="submit">
            <c:choose>
                <c:when test="${activity.id > 0}"> Cập nhât hoạt động</c:when>
                <c:otherwise> Thêm hoạt động</c:otherwise>
            </c:choose>
        </button>
    </div>
</form>