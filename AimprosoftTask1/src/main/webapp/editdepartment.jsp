<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: VanDuc
  Date: 01.12.2020
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<h2>Edit Department Page</h2>
<div>
    <%
        int id = Integer.parseInt(request.getParameter("departmentid"));
        String name = request.getParameter("departmentname");
        request.setAttribute("id",id);
        request.setAttribute("name",name);
    %>
    <c:if test="${not empty status}">
        <div class="status">
            <h3> ${status}</h3>
        </div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="error">
            <c:forEach var="errors" items="${error}">
                <h3> ${errors.value}</h3>
            </c:forEach>
        </div>
    </c:if>
    <form action="/updatedepartment" class="form-container" method="post">
        <label><b>Department Name</b>
            <input type="hidden" id="departmentid" name="departmentid" value="${id}" required>
            <input type="text" id="departmentname" name="departmentname" value="${name}" required>
        </label>
    <input type="submit" class="btn btn-success">
    </form>
</div>

</body>
</html>
