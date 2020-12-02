<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/style.css">
    <title>Department Table</title>
</head>
<body>
<div>
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
    <h2>Department Table</h2>
    <table class="table m-0">
        <thead>
        <tr>
            <th>ID</th>
            <th>Department name</th>
            <th>
                <button class="btn btn-success" onclick="openAddForm()">Add Department</button>

                <div class="form-popup" id="addForm">
                    <form action="/adddepartment" class="form-container" method="post">
                        <label><b>Department Name</b></label>
                        <input type="text" placeholder="Enter Name" name="departmentname" required>
                        <button type="submit" class="btn">Submit</button>
                        <button type="button" class="btn cancel" onclick="closeForm()">Close</button>
                    </form>
                </div>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${departments}" var="department">
            <tr>
                <th scope="row">${department.id}</th>
                <td>${department.name}</td>
                <td>
                    <!-- Call to action buttons -->
                    <ul class="list-inline m-0">
                        <li class="list-inline-item">
                            <a class="btn btn-success"
                               href="editdepartment.jsp?departmentid=${department.id}&departmentname=${department.name}">Edit</a>
                        </li>
                        <li class="list-inline-item">
                            <form action="/deletedepartment" method="post">
                                <input type="hidden" name="departmentid" value="${department.id}">
                                <button type="submit" class="btn btn-danger">Remove</button>
                            </form>
                        </li>
                        <li class="list-inline-item">
                            <form action="/employees" method="get">
                                <input type="hidden" name="departmentid" value="${department.id}">
                                <button type="submit" class="btn btn-danger">Show Employees</button>
                            </form>
                        </li>
                    </ul>
                </td>
            </tr>
        </c:forEach>
        <tr>
        </tr>
        </tbody>
    </table>
</div>
</body>
<script src="js/form.js"></script>
</html>
