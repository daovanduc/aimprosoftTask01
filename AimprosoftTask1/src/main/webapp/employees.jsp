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

        /*the container must be positioned relative:*/
        .custom-select {
            position: relative;
            font-family: Arial;
        }

        .custom-select select {
            display: none; /*hide original SELECT element:*/
        }

        /*style the arrow inside the select element:*/
        .select-selected:after {
            position: absolute;
            content: "";
            top: 14px;
            right: 10px;
            width: 0;
            height: 0;
            border: 6px solid transparent;
            border-color: #fff transparent transparent transparent;
        }

        /*point the arrow upwards when the select box is open (active):*/
        .select-selected.select-arrow-active:after {
            border-color: transparent transparent #fff transparent;
            top: 7px;
        }

        /*style the items (options), including the selected item:*/
        .select-items div,.select-selected {
            border-color: transparent transparent rgba(0, 0, 0, 0.1) transparent;
            cursor: pointer;
            user-select: none;
        }

        /*style items (options):*/
        .select-items {
            position: absolute;
            background-color: #ffffff;
            top: 100%;
            left: 0;
            right: 0;
            z-index: 99;
        }

        /*hide the items when the select box is closed:*/
        .select-hide {
            display: none;
        }

        .select-items div:hover, .same-as-selected {
            background-color: rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
<div>
    <c:choose>
        <c:when test="${not empty status}">
            <div class="status">
                <h3> ${status}</h3>
            </div>
        </c:when>
        <c:when test="${not empty error}">
            <div class="error">
                <c:forEach var="errors" items="${error}">
                    <h3> ${errors.value}</h3>
                </c:forEach>
            </div>
        </c:when>
    </c:choose>
    <h2>Employee Table</h2>
    <table class="table m-0">
        <thead>
        <tr>
            <th>ID</th>
            <th>Employee name</th>
            <th>Is Assigned to</th>
            <th>
                <button class="btn btn-success" onclick="openAddForm()">Add Employee</button>

                <div class="form-popup" id="addForm">
                    <form action="/addemployee" class="form-container" method="post">
                        <label><b>Department Name</b></label>
                        <label>
                            <input type="text" placeholder="Enter Name" name="employeename" required>
                        </label>
                        <div class="custom-select" style="width:200px;">
                            <select name="departmentid">
                                <c:forEach items="${departments}" var="department">
                                    <option value="${department.id}">${department.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <br>
                        <button type="submit" class="btn">Submit</button>
                        <button type="button" class="btn cancel" onclick="closeForm()">Close</button>
                    </form>
                </div>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${employees}" var="employee">
            <tr>
                <th scope="row">${employee.id}</th>
                <td>${employee.name}</td>
                <td>${employee.departmentName}</td>
                <td>
                    <!-- Call to action buttons -->
                    <ul class="list-inline m-0">
                        <li class="list-inline-item">
                            <a class="btn btn-success" href="editemployee.jsp?employeeid=${employee.id}&employeename=${employee.name}">Edit</a>
                        </li>
                        <li class="list-inline-item">
                            <form action="/deleteemployee" method="post">
                                <input type="hidden" name="employeeid" value="${employee.id}">
                                <button type="submit" class="btn btn-danger">Remove</button>
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
<script src="js/dropdown.js"></script>
<script src="js/form.js"></script>
</body>
</html>
