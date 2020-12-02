package com.dao.aimprosoft.service;

import com.dao.aimprosoft.model.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartment();

    boolean insertDepartment(String name);

    boolean deleteDepartment(int id);

    boolean updateDepartment(int id, String name);
}
