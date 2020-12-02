package com.dao.aimprosoft.util;

import java.sql.Connection;

public class ConnectionHolder {
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static ThreadLocal<Connection> getThreadLocal() {
        return threadLocal;
    }

    public void setThreadLocal(Connection connection) {
        threadLocal.set(connection);
    }

    public Connection getConnection() {
        return threadLocal.get();
    }

}
