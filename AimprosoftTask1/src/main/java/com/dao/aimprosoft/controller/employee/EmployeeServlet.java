package com.dao.aimprosoft.controller.employee;

import com.dao.aimprosoft.model.Department;
import com.dao.aimprosoft.model.EmployeeData;
import com.dao.aimprosoft.service.DepartmentService;
import com.dao.aimprosoft.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.dao.aimprosoft.util.constant.ApplicationConstant.*;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {

    private static final String EMPLOYEES_PAGE = "employees.jsp";

    private EmployeeService employeeService;
    private DepartmentService departmentService;

    @Override
    public void init() throws ServletException {
        employeeService = (EmployeeService) getServletContext().getAttribute(EMPLOYEE_SERVICE);
        departmentService = (DepartmentService) getServletContext().getAttribute(DEPARTMENT_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<EmployeeData> employeeData = employeeService.getAllEmployees();
        List<Department> departments = departmentService.getAllDepartment();
        req.setAttribute(EMPLOYEE, employeeData);
        req.setAttribute(DEPARTMENTS, departments);
        req.getRequestDispatcher(EMPLOYEES_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


}
