package com.dao.aimprosoft.controller.department;

import com.dao.aimprosoft.service.DepartmentService;
import com.dao.aimprosoft.util.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import static com.dao.aimprosoft.util.Validator.VALIDATION_REGEX;
import static com.dao.aimprosoft.util.constant.ApplicationConstant.*;
import static com.dao.aimprosoft.util.constant.MessageConstant.SUCCESSFUL_UPDATED_DEPARTMENT_MESSAGE;
import static com.dao.aimprosoft.util.constant.MessageConstant.WRONG_INPUT_DEPARTMENT_NAME_MESSAGE;

@WebServlet("/updatedepartment")
public class UpdateDepartmentServlet extends HttpServlet {

    private DepartmentService departmentService;
    private Validator validator;

    @Override
    public void init() throws ServletException {
        departmentService = (DepartmentService) getServletContext().getAttribute(DEPARTMENT_SERVICE);
        validator = (Validator) getServletContext().getAttribute(VALIDATOR);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter(DEPARTMENT_ID));
        String name = req.getParameter(DEPARTMENT_NAME);
        HashMap<String, String> errors = new HashMap<>();
        if (validator.validateName(name, errors, VALIDATION_REGEX, WRONG_INPUT_DEPARTMENT_NAME_MESSAGE) &&
                departmentService.updateDepartment(id, name)) {
            req.setAttribute(STATUS, SUCCESSFUL_UPDATED_DEPARTMENT_MESSAGE);
        } else {
            req.setAttribute(ERROR, errors);
        }
        req.getRequestDispatcher(DEPARTMENTS).forward(req, resp);
    }
}
