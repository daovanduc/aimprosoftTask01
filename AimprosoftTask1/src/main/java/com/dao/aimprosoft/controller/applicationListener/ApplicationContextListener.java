package com.dao.aimprosoft.controller.applicationListener;

import com.dao.aimprosoft.dao.DefaultDepartmentDao;
import com.dao.aimprosoft.dao.DefaultEmployeeDao;
import com.dao.aimprosoft.dao.DepartmentDAO;
import com.dao.aimprosoft.dao.EmployeeDAO;
import com.dao.aimprosoft.service.DefaultDepartmentService;
import com.dao.aimprosoft.service.DefaultEmployeeService;
import com.dao.aimprosoft.service.DepartmentService;
import com.dao.aimprosoft.service.EmployeeService;
import com.dao.aimprosoft.service.transaction.TransactionManager;
import com.dao.aimprosoft.util.Validator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import static com.dao.aimprosoft.util.constant.ApplicationConstant.*;

public class ApplicationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionManager connectionManager = new ConnectionManager();
        connectionManager.init();
        TransactionManager transactionManager = new TransactionManager(connectionManager);
        initComponent(sce.getServletContext(), transactionManager, connectionManager);

    }

    private void initComponent(ServletContext servletContext, TransactionManager transactionManager, ConnectionManager connectionManager) {
        EmployeeDAO employeeDAO = new DefaultEmployeeDao();
        EmployeeService employeeService = new DefaultEmployeeService(transactionManager, employeeDAO);
        servletContext.setAttribute(EMPLOYEE_SERVICE, employeeService);

        DepartmentDAO departmentDAO = new DefaultDepartmentDao();
        DepartmentService departmentService = new DefaultDepartmentService(transactionManager, departmentDAO);
        servletContext.setAttribute(DEPARTMENT_SERVICE, departmentService);

        Validator validator = new Validator();
        servletContext.setAttribute(VALIDATOR, validator);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
