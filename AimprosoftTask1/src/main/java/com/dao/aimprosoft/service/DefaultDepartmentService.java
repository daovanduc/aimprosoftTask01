package com.dao.aimprosoft.service;

import com.dao.aimprosoft.dao.DepartmentDAO;
import com.dao.aimprosoft.model.Department;
import com.dao.aimprosoft.service.transaction.TransactionManager;

import java.util.*;

public class DefaultDepartmentService implements DepartmentService {

    private TransactionManager transactionManager;
    private DepartmentDAO departmentDAO;

    public DefaultDepartmentService(TransactionManager transactionManager, DepartmentDAO departmentDAO) {
        this.transactionManager = transactionManager;
        this.departmentDAO = departmentDAO;
    }

    @Override
    public List<Department> getAllDepartment() {
        return transactionManager.doTransaction(() -> {
            Set<Department> departments = departmentDAO.getAllDepartment();
            List<Department> departmentList = new ArrayList<>(departments);
            departmentList.sort(Comparator.comparing(Department::getId));
            return departmentList;
        });
    }

    @Override
    public boolean insertDepartment(String name) {
        return transactionManager.doTransaction(() -> departmentDAO.insertDepartment(name));
    }

    @Override
    public boolean deleteDepartment(int id) {
        return transactionManager.doTransaction(() -> departmentDAO.deleteDepartment(id));
    }

    @Override
    public boolean updateDepartment(int id, String name) {
        return transactionManager.doTransaction(()-> departmentDAO.updateDepartment(id,name));
    }
}
