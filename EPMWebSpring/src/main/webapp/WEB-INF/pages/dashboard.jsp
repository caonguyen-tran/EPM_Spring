<%-- 
    Document   : dashboard
    Created on : May 26, 2024, 9:51:07 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h2>
    Statistic
</h2>

<div style="height: 60px; width: 100%;display: flex; align-items: center; justify-content: space-between; flex-direction: row">
    <div style="display: flex; flex-direction: row">
        <div class="form-floating" style="margin-bottom: 12px; width: 250px">
            <select class="form-select" aria-label="Default select example" name="facultyId">
                <c:forEach items="${faculties}" var="f">
                    <option value="${f.id}">${f.name}</option>
                </c:forEach>
            </select>
            <label for="categoryId" class="form-label">Khoa:</label>
        </div>
    </div>
    <button class="btn btn-primary" type="button" style="width: 80px; margin-bottom: 5px">Export</button>
</div>

<div style="min-height: 700px">
    
</div>


