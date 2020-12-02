package com.dao.aimprosoft.controller.department;

import com.dao.aimprosoft.model.Department;
import com.dao.aimprosoft.service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.dao.aimprosoft.util.constant.ApplicationConstant.*;

@WebServlet("/departments")
public class DepartmentServlet extends HttpServlet {


    private DepartmentService departmentService;

    @Override
    public void init() throws ServletException {
        departmentService = (DepartmentService) getServletContext().getAttribute(DEPARTMENT_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> departments = departmentService.getAllDepartment();
        req.setAttribute(DEPARTMENTS, departments);
        req.getRequestDispatcher(req.getContextPath() + DEPARTMENT_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
