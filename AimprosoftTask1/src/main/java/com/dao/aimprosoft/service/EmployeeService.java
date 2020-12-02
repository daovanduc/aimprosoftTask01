package com.dao.aimprosoft.service;

import com.dao.aimprosoft.model.Employee;
import com.dao.aimprosoft.model.EmployeeData;

import java.util.List;

public interface EmployeeService {
    List<EmployeeData> getEmployeeByDepartmentId(int id);

    List<EmployeeData> getAllEmployees();

    boolean insertEmployee(String employeename, int departmentid);

    boolean deleteEmployee(int id);

    boolean updateEmployee(int id, String name, int departmentId);

}
