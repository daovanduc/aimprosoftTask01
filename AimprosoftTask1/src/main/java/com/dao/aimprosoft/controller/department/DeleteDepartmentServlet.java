package com.dao.aimprosoft.controller.department;

import com.dao.aimprosoft.service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.dao.aimprosoft.util.constant.ApplicationConstant.*;
import static com.dao.aimprosoft.util.constant.MessageConstant.FAIL_TO_DELETE_DEPARTMENT_MESSAGE;
import static com.dao.aimprosoft.util.constant.MessageConstant.SUCCESSFUL_DELETED_DEPARTMENT_MESSAGE;

@WebServlet("/deletedepartment")
public class DeleteDepartmentServlet extends HttpServlet {


    private DepartmentService departmentService;

    @Override
    public void init() {
        departmentService = (DepartmentService) getServletContext().getAttribute(DEPARTMENT_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter(DEPARTMENT_ID));
        if (id > 0 && departmentService.deleteDepartment(id)) {
            req.setAttribute(STATUS, SUCCESSFUL_DELETED_DEPARTMENT_MESSAGE);
        } else {
            req.setAttribute(STATUS, FAIL_TO_DELETE_DEPARTMENT_MESSAGE);
        }
        req.getRequestDispatcher(DEPARTMENTS).forward(req, resp);

    }
}
