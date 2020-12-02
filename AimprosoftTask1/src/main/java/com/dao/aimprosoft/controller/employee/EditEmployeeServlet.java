package com.dao.aimprosoft.controller.employee;

import com.dao.aimprosoft.model.Department;
import com.dao.aimprosoft.service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.dao.aimprosoft.util.constant.ApplicationConstant.DEPARTMENT_SERVICE;

@WebServlet("/editemployee")
public class EditEmployeeServlet extends HttpServlet {

    private DepartmentService departmentService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> departments = departmentService.getAllDepartment();

    }

    @Override
    public void init() throws ServletException {
        departmentService = (DepartmentService) getServletContext().getAttribute(DEPARTMENT_SERVICE);
    }
}
