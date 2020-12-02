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
import java.util.Objects;

import static com.dao.aimprosoft.util.constant.ApplicationConstant.*;

@WebServlet("/employees")
public class EmployeesByDepartmentServlet extends HttpServlet {

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
        int id = getId(req);
        if (id > 0){
            List<EmployeeData> employeeData = employeeService.getEmployeeByDepartmentId(id);
            List<Department> departments = departmentService.getAllDepartment();
            req.setAttribute(EMPLOYEE, employeeData);
            req.setAttribute(DEPARTMENTS, departments);
            req.getRequestDispatcher(EMPLOYEES_PAGE).forward(req, resp);
        }

    }
    private int getId(HttpServletRequest req) {
        String id = req.getParameter(DEPARTMENT_ID);
        if (Objects.isNull(id)){
            return 0;
        }
        return Integer.parseInt(id);
    }
}
