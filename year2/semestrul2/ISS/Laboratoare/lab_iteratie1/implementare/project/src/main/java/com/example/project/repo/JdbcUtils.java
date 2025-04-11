package com.example.project.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private Properties jdbcProps;

    public JdbcUtils(Properties props) {
        jdbcProps = props;
    }

    private Connection instance = null;

    private Connection getNewConnection() {
        String url = jdbcProps.getProperty("jdbc.url");
        String user = jdbcProps.getProperty("jdbc.user");
        String pass = jdbcProps.getProperty("jdbc.pass");

        System.out.println("Connecting to: " + url);

        Connection con = null;
        try {
            if (user != null && pass != null)
                con = DriverManager.getConnection(url, user, pass);
            else
                con = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.err.println("Error getting connection: " + e.getMessage());
        }
        return con;
    }

    public Connection getConnection() {
        try {
            if (instance == null || instance.isClosed())
                instance = getNewConnection();
        } catch (SQLException e) {
            System.err.println("Error checking/creating connection: " + e.getMessage());
        }
        return instance;
    }

    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("jdbc.url", "jdbc:postgresql://localhost:5432/BugsDB");
        props.setProperty("jdbc.user", "plaiurares");
        props.setProperty("jdbc.pass", "antimagu");

        JdbcUtils jdbcUtils = new JdbcUtils(props);
        Connection con = jdbcUtils.getConnection();

        if (con != null) {
            System.out.println("PostgreSQL connection successful!");
        } else {
            System.err.println("Failed to connect to PostgreSQL.");
        }
    }
}
