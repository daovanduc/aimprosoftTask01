package com.dao.aimprosoft.dao;

import com.dao.aimprosoft.model.Department;

import java.util.Set;

public interface DepartmentDAO {
    Set<Department> getAllDepartment();

    Department getDepartmentById();

    boolean deleteDepartment(int id);

    boolean updateDepartment(int id, String name);

    boolean insertDepartment(String name);
}
