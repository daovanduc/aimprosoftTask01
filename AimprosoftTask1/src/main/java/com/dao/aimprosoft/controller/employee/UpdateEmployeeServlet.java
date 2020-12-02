package com.dao.aimprosoft.controller.employee;

import com.dao.aimprosoft.service.EmployeeService;
import com.dao.aimprosoft.util.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import static com.dao.aimprosoft.util.Validator.NUMERIC_REGEX;
import static com.dao.aimprosoft.util.constant.ApplicationConstant.*;
import static com.dao.aimprosoft.util.constant.MessageConstant.SUCCESSFUL_UPDATED_EMPLOYEE_MESSAGE;
import static com.dao.aimprosoft.util.constant.MessageConstant.WRONG_INPUT_EMPLOYEE_NAME_MESSAGE;

@WebServlet("/updateemployee")
public class UpdateEmployeeServlet extends HttpServlet {

    private EmployeeService employeeService;
    private Validator validator;

    @Override
    public void init() throws ServletException {
        employeeService = (EmployeeService) getServletContext().getAttribute(EMPLOYEE_SERVICE);
        validator = (Validator) getServletContext().getAttribute(VALIDATOR);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int employeeId = Integer.parseInt(req.getParameter(EMPLOYEE_ID));
        int departmentId = Integer.parseInt(req.getParameter(DEPARTMENT_ID));
        String employeeName = req.getParameter(EMPLOYEE_NAME);
        HashMap<String, String> errors = new HashMap<>();
        if (validator.validateName(employeeName, errors, NUMERIC_REGEX, WRONG_INPUT_EMPLOYEE_NAME_MESSAGE) &&
                employeeId != 0 &&
                departmentId != 0 &&
                employeeService.updateEmployee(employeeId, employeeName, departmentId)) {
            req.setAttribute(STATUS, SUCCESSFUL_UPDATED_EMPLOYEE_MESSAGE);
        } else {
            req.setAttribute(ERROR, errors);
        }
        req.getRequestDispatcher(EMPLOYEE_SERVLET).forward(req, resp);
    }
}
