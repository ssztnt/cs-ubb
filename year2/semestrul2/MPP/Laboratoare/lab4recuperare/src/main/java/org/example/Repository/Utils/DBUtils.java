package org.example.Repository.Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
    private Properties properties;
    private Connection connection;
    private static final Logger logger = LogManager.getLogger();

    public DBUtils(Properties properties) {
        this.properties = properties;
        initializeConnection();
    }

    private void initializeConnection() {
        logger.traceEntry();
        try {
            String url = properties.getProperty("jdbc.url");
            String user = properties.getProperty("jdbc.user");
            String pass = properties.getProperty("jdbc.password");
            logger.info("Trying to connect to the database {}", url);

            if (url != null) {
                if (user != null && !user.isEmpty() && pass != null && !pass.isEmpty()) {
                    logger.info("User {}", user);
                    logger.info("Password {}", pass);
                    connection = DriverManager.getConnection(url, user, pass);
                } else {
                    connection = DriverManager.getConnection(url);
                }
            } else {
                throw new SQLException("Database URL not specified");
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error: " + e);
        }
        logger.traceExit();
    }

    public Connection getConnection() {
        logger.traceEntry();
        logger.traceExit(connection);
        return connection;
    }
}