<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.dao.aimprosoft.service.DepartmentService" %>
<%@ page import="static com.dao.aimprosoft.util.constant.ApplicationConstant.DEPARTMENT_SERVICE" %>
<%@ page import="com.dao.aimprosoft.model.Department" %>
<%@ page import="java.util.List" %><%--
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
    <style>
        .status {
            color: #4CAF50;
        }

        .error {
            color: red;
        }

        /* The popup form - hidden by default */
        .form-popup {
            display: none;
            position: fixed;
            right: 15px;
            border: 3px solid #f1f1f1;
            z-index: 9;
        }

        /* Add styles to the form container */
        .form-container {
            max-width: 300px;
            padding: 10px;
            background-color: white;
        }

        /* Full-width input fields */
        .form-container input {
            width: 100%;
            padding: 15px;
            margin: 5px 0 22px 0;
            border: none;
            background: #f1f1f1;
        }

        /* Set a style for the submit/login button */
        .form-container .btn {
            background-color: #4CAF50;
            color: white;
            padding: 16px 20px;
            border: none;
            cursor: pointer;
            width: 100%;
            margin-bottom: 10px;
            opacity: 0.8;
        }

        /* Add a red background color to the cancel button */
        .form-container .cancel {
            background-color: red;
        }

        /* Add some hover effects to buttons */
        .form-container .btn:hover, .open-button:hover {
            opacity: 1;
        }
    </style>
</head>
<body>
<h2>Edit Employee</h2>
<div>
    <%
        int id = Integer.parseInt(request.getParameter("employeeid"));
        String name = request.getParameter("employeename");
        DepartmentService departmentService = (DepartmentService) request.getServletContext().getAttribute(DEPARTMENT_SERVICE);
        List<Department> departments = departmentService.getAllDepartment();
        request.setAttribute("departments", departments);
        request.setAttribute("id",id);
        request.setAttribute("name",name);
    %>
    <form action="/updateemployee" class="form-container" method="post">
        <label><b>Department Name</b>
            <input type="hidden" id="employeeid" name="employeeid" value="${id}" required>
            <input type="text" id="employeename" name="employeename" value="${name}"" required>
        </label>
        <div class="custom-select" style="width:280px;">
            <select id="departmentid" name="departmentid">
                <c:forEach items="${departments}" var="department">
                    <option value="${department.id}">${department.name}</option>
                </c:forEach>
            </select>
        </div>
        <input type="submit" class="btn btn-success">
    </form>
</div>

</body>
</html>
