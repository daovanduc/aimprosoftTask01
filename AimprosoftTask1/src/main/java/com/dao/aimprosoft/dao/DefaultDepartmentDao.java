package com.dao.aimprosoft.dao;

import com.dao.aimprosoft.model.Department;
import com.dao.aimprosoft.util.ConnectionHolder;
import com.dao.aimprosoft.util.ResourceCloser;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DefaultDepartmentDao implements DepartmentDAO {

    private static final int PARAMETER_INDEX = 1;

    @Override
    public Set<Department> getAllDepartment() {
        Set<Department> departments = new HashSet<>();
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = ConnectionHolder.getThreadLocal().get();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM department");
            while (resultSet.next()) {
                departments.add(extractDepartment(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResourceCloser.close(resultSet, statement);
        }
        return departments;
    }

    @Override
    public Department getDepartmentById() {
        return null;
    }

    @Override
    public boolean deleteDepartment(int id) {
        boolean isDeleted = false;
        Connection connection = ConnectionHolder.getThreadLocal().get();
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM department WHERE id = ?")){
            preparedStatement.setInt(PARAMETER_INDEX, id);
            isDeleted = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDeleted;
    }

    @Override
    public boolean updateDepartment(int id, String name) {
        int k = 1;
        boolean isUpdated = false;
        Connection connection = ConnectionHolder.getThreadLocal().get();
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE department SET department_name = ? WHERE id = ?")){
            preparedStatement.setString(k++, name);
            preparedStatement.setInt(k, id);
            isUpdated = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    @Override
    public boolean insertDepartment(String name) {
        boolean isAdded = false;
        Connection connection = ConnectionHolder.getThreadLocal().get();
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO department(department_name) VALUES (?)")){
            preparedStatement.setString(PARAMETER_INDEX, name);
            isAdded = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isAdded;
    }

    private Department extractDepartment(ResultSet resultSet) throws SQLException {
        return new Department(resultSet.getInt("id"), resultSet.getString("department_name"));
    }
}
