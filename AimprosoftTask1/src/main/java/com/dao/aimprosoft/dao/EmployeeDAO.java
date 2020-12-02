package com.dao.aimprosoft.dao;

import com.dao.aimprosoft.model.Employee;
import com.dao.aimprosoft.model.EmployeeData;

import java.util.List;
import java.util.Set;

public interface EmployeeDAO {
    List<EmployeeData> getAllEmployee();

    Set<EmployeeData> getEmployeeByDepartmentId(int id);

    boolean insertEmployee(String employeeName, int departmentId);

    boolean deleteEmployeeById(int id);

    boolean updateEmployeeById(Employee employee);
}
