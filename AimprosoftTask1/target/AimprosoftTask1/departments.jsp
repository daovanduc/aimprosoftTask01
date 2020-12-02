<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
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
                            <a class="btn btn-success" href="editdepartment.jsp?departmentid=${department.id}&departmentname=${department.name}">Edit</a>
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
<script>
    function openAddForm() {
        document.getElementById("addForm").style.display = "block";
    }

    function openEditForm() {
        document.getElementById("editForm").style.display = "block";
    }

    function closeForm() {
        document.getElementById("addForm").style.display = "none";
        document.getElementById("editForm").style.display = "none";
    }
</script>
</body>
</html>
