package com.dao.aimprosoft.dao;

import com.dao.aimprosoft.model.Employee;
import com.dao.aimprosoft.model.EmployeeData;
import com.dao.aimprosoft.util.ConnectionHolder;
import com.dao.aimprosoft.util.ResourceCloser;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DefaultEmployeeDao implements EmployeeDAO {

    private static final String SELECT_ALL_EMPLOYEES = "SELECT Employee.id, Employee.name, Department.department_name FROM Employee LEFT JOIN Department ON Employee.department_id=Department.id;";
    private static final String DELETE_EMPLOYEE_BY_ID = "DELETE FROM Employee WHERE id = ?";
    private static final String UPDATE_EMPLOYEE = "UPDATE Employee SET name = ?, department_id = ? WHERE id =?";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DEPARTMENT_ID = "departmentId";
    private static final int PARAMETER_INDEX = 1;
    private static final String SELECT_EMPLOYEES_BY_DEPARTMENT_ID = "SELECT Employee.id, Employee.name, Department.department_name FROM Employee INNER JOIN Department ON Employee.department_id=Department.id WHERE Department.id = ?;";
    private static final String DEPARTMENT_NAME = "department_name";

    public DefaultEmployeeDao() {
    }

    @Override
    public List<EmployeeData> getAllEmployee() {
        List<EmployeeData> employees = new ArrayList<>();
        Connection connection = ConnectionHolder.getThreadLocal().get();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_EMPLOYEES);
            while (resultSet.next()) {
                employees.add(extractEmployeeData(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResourceCloser.close(statement, resultSet);
        }
        return employees;
    }

    @Override
    public Set<EmployeeData> getEmployeeByDepartmentId(int department_id) {
        Connection connection = ConnectionHolder.getThreadLocal().get();
        Set<EmployeeData> employees = new HashSet<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_EMPLOYEES_BY_DEPARTMENT_ID);
            preparedStatement.setInt(PARAMETER_INDEX, department_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employees.add(extractEmployeeData(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResourceCloser.close(preparedStatement, resultSet);
        }
        return employees;
    }

    @Override
    public boolean insertEmployee(String employeeName, int departmentId) {
        boolean isAdded = false;
        int k = 1;
        Connection connection = ConnectionHolder.getThreadLocal().get();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO employee(name, department_id) VALUES (?,?)");
            preparedStatement.setString(k++, employeeName);
            preparedStatement.setInt(k, departmentId);
            isAdded = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResourceCloser.close(preparedStatement);
        }
        return isAdded;
    }

    @Override
    public boolean deleteEmployeeById(int id) {
        boolean isDeleted = false;
        Connection connection = ConnectionHolder.getThreadLocal().get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE_BY_ID)) {
            preparedStatement.setInt(PARAMETER_INDEX, id);
            isDeleted = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDeleted;
    }


    @Override
    public boolean updateEmployeeById(Employee employee) {
        boolean isUpdated = false;
        Connection connection = ConnectionHolder.getThreadLocal().get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE)) {
            int k = 1;
            preparedStatement.setString(k++, employee.getName());
            preparedStatement.setInt(k++, employee.getDepartmentId());
            preparedStatement.setInt(k, employee.getId());
            isUpdated = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    private Employee extractEmployee(ResultSet resultSet) throws SQLException {
        return new Employee(resultSet.getInt(ID),
                resultSet.getString(NAME),
                resultSet.getInt(DEPARTMENT_ID));
    }

    private EmployeeData extractEmployeeData(ResultSet resultset) throws SQLException {
        return new EmployeeData(resultset.getInt(ID),
                resultset.getString(NAME),
                resultset.getString(DEPARTMENT_NAME));

    }
}
