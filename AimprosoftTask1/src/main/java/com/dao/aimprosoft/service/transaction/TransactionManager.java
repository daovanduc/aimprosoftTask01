package com.dao.aimprosoft.service.transaction;

import com.dao.aimprosoft.controller.applicationListener.ConnectionManager;
import com.dao.aimprosoft.util.ConnectionHolder;
import com.dao.aimprosoft.util.ResourceCloser;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private ConnectionManager connectionManager;

    public TransactionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public <T> T doTransaction(Operation<T> operation) {
        Connection connection = connectionManager.getConnection();
        ConnectionHolder.getThreadLocal().set(connection);
        try {
            connection.setAutoCommit(false);
            T res = operation.action();
            connection.commit();
            return res;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException("roll back exception");
            }
            throw new RuntimeException("transaction exception");
        } finally {
            ConnectionHolder.getThreadLocal().remove();
            ResourceCloser.close(connection);
        }
    }
}
