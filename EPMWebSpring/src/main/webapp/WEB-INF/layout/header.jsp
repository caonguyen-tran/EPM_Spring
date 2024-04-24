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
         <a class="nav-link" href="<c:url value="/register" />">Tạo hoạt động</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<c:url value="/report" />">Báo thiếu hoạt động</a>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#">Disabled</a>
      </li>
    </ul>
  </div>
</nav>