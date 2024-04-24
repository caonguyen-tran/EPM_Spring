<%-- 
    Document   : index
    Created on : Apr 11, 2024, 7:06:09 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1>Trang nay cua Admin</h1>


<p>Danh sach sinh vien</p>

<ul>
    <c:forEach items="${students}" var="s">
        <li>
            ${s.firstname}
        </li>
    </c:forEach>
</ul>