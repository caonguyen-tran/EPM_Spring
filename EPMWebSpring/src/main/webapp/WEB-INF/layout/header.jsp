<%-- 
    Document   : header
    Created on : Apr 20, 2024, 10:59:54 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Grey with black text -->
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link active" href="<c:url value="/" />">E-Point</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/" />">Danh sách hoạt động</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/report" />">Báo thiếu</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/join" />">Danh sách tham gia</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/register" />">Danh sách đăng ký</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/activity"/>">Tạo hoạt động</a>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="#">
                    disabled
                </a>
            </li>
            
        </ul><c:choose>
            <c:when test="${pageContext.request.userPrincipal.name == null}">
                <li class="nav-item">
                    <a class=" btn btn-info " href="<c:url value="/login" />">Đăng nhập</a>
                </li>
            </c:when>
            <c:when test="${pageContext.request.userPrincipal.name != null}">
                <h4 style="color: white">Chào ${pageContext.request.userPrincipal.name}!</h4>
                <li class="nav-item">
                    <a class=" btn btn-info " href="<c:url value="/logout" />">Đăng xuất</a>
                </li>
            </c:when>
        </c:choose>
    </div>
</nav>