package com.dao.aimprosoft.controller.applicationListener;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private static final String DRIVER = "driver";
    private static final String URL = "url";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final int INITIAL_SIZE = 5;
    private static final int MAX_TOTAL = 2;
    private static final String APP_PROPERTIES = "app.properties";

    private BasicDataSource dataSource;

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("cannot get connection", e);
        }
    }

    public void init() {
        dataSource = new BasicDataSource();
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            Properties props = new Properties();
            try (InputStream resourceStream = loader.getResourceAsStream(APP_PROPERTIES)) {
                props.load(resourceStream);
            }
            dataSource.setDriverClassName(props.getProperty(DRIVER));
            dataSource.setUrl(props.getProperty(URL));
            dataSource.setUsername(props.getProperty(USERNAME));
            dataSource.setPassword(props.getProperty(PASSWORD));
            dataSource.setMinIdle(5);
            dataSource.setMaxIdle(10);
            dataSource.setInitialSize(INITIAL_SIZE);
            dataSource.setMaxTotal(MAX_TOTAL);
        } catch (IOException e) {
            throw new RuntimeException("Cannot init dataSource connection", e);
        }
    }
}
