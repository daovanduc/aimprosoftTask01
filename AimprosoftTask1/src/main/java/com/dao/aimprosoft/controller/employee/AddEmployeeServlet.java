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
import static com.dao.aimprosoft.util.constant.MessageConstant.ADDED_NEW_EMPLOYEE_SUCCESSFUL_MESSAGE;
import static com.dao.aimprosoft.util.constant.MessageConstant.WRONG_INPUT_EMPLOYEE_NAME_MESSAGE;

@WebServlet("/addemployee")
public class AddEmployeeServlet extends HttpServlet {

    private EmployeeService employeeService;
    private Validator validator;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeeName = req.getParameter(EMPLOYEE_NAME);
        int departmentId = Integer.parseInt(req.getParameter(DEPARTMENT_ID));
        HashMap<String, String> errors = new HashMap<>();
        if (departmentId > 0 &&
                validator.validateName(employeeName, errors, NUMERIC_REGEX, WRONG_INPUT_EMPLOYEE_NAME_MESSAGE) &&
                employeeService.insertEmployee(employeeName, departmentId)) {
            req.setAttribute(STATUS, ADDED_NEW_EMPLOYEE_SUCCESSFUL_MESSAGE);
        } else {
            req.setAttribute(ERROR, errors);
        }
        req.getRequestDispatcher(EMPLOYEE_SERVLET).forward(req, resp);

    }


    @Override
    public void init() throws ServletException {
        employeeService = (EmployeeService) getServletContext().getAttribute(EMPLOYEE_SERVICE);
        validator = (Validator) getServletContext().getAttribute(VALIDATOR);
    }
}
