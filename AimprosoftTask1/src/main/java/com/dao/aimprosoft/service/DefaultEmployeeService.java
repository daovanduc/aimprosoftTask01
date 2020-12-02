package com.dao.aimprosoft.service;

import com.dao.aimprosoft.dao.EmployeeDAO;
import com.dao.aimprosoft.model.Employee;
import com.dao.aimprosoft.model.EmployeeData;
import com.dao.aimprosoft.service.transaction.TransactionManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DefaultEmployeeService implements EmployeeService {

    private TransactionManager transactionManager;
    private EmployeeDAO employeeDAO;

    public DefaultEmployeeService(TransactionManager transactionManager, EmployeeDAO employeeDAO) {
        this.transactionManager = transactionManager;
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<EmployeeData> getEmployeeByDepartmentId(int id) {
        return transactionManager.doTransaction(() -> {
            List<EmployeeData> employeeData = new ArrayList<>(employeeDAO.getEmployeeByDepartmentId(id));
            employeeData.sort(Comparator.comparing(EmployeeData::getId));
            return employeeData;
        });
    }

    @Override
    public List<EmployeeData> getAllEmployees() {
        return transactionManager.doTransaction(() -> employeeDAO.getAllEmployee());
    }

    @Override
    public boolean insertEmployee(String employeename, int departmentid) {
        return transactionManager.doTransaction(() -> employeeDAO.insertEmployee(employeename, departmentid));
    }

    @Override
    public boolean deleteEmployee(int id) {
        return transactionManager.doTransaction(()-> employeeDAO.deleteEmployeeById(id));
    }

    @Override
    public boolean updateEmployee(int id, String name, int departmentId) {
        return transactionManager.doTransaction(() -> employeeDAO.updateEmployeeById(new Employee(id, name, departmentId)));
    }


}
