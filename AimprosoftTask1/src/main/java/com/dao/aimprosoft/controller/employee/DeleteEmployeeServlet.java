package com.dao.aimprosoft.controller.employee;

import com.dao.aimprosoft.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.dao.aimprosoft.util.constant.ApplicationConstant.*;
import static com.dao.aimprosoft.util.constant.MessageConstant.FAIL_TO_DELETE_EMPLOYEE_MESSAGE;
import static com.dao.aimprosoft.util.constant.MessageConstant.SUCCESSFUL_DELETED_EMPLOYEE_MESSAGE;


@WebServlet("/deleteemployee")
public class DeleteEmployeeServlet extends HttpServlet {

    private EmployeeService employeeService;

    @Override
    public void init() throws ServletException {
        employeeService = (EmployeeService) getServletContext().getAttribute(EMPLOYEE_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int employeeId = Integer.parseInt(req.getParameter(EMPLOYEE_ID));
        if (employeeId > 0 && employeeService.deleteEmployee(employeeId)) {
            req.setAttribute(STATUS, SUCCESSFUL_DELETED_EMPLOYEE_MESSAGE);
        } else {
            req.setAttribute(STATUS, FAIL_TO_DELETE_EMPLOYEE_MESSAGE);
        }
        req.getRequestDispatcher(EMPLOYEE_SERVLET).forward(req, resp);

    }
}
