package DAO;

import java.sql.*;
import java.util.Properties;

public class MySQLCon {
    // Initialize connection variables.
    String host = "universitydtudb.mysql.database.azure.com";
    String database = "cdiofinaldb";
    String user = "myadmin@universitydtudb";
    String password = "2GpZ#P/h{&";
    Connection connection = null;

    // check that the driver is installed
    public void setupCon() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("MariaDB driver detected");
        } catch (
                ClassNotFoundException e) {
            throw new ClassNotFoundException("MariaDB JDBC driver NOT detected in library path.", e);
        }


        // Initialize connection object
        try {
            String url = String.format("jdbc:mariadb://%s/%s", host, database);

            // Set connection properties.
            Properties properties = new Properties();
            properties.setProperty("user", user);
            properties.setProperty("password", password);
            properties.setProperty("useSSL", "true");
            properties.setProperty("verifyServerCertificate", "true");
            properties.setProperty("requireSSL", "false");

            // get connection
            connection = DriverManager.getConnection(url, properties);
            System.out.println("Connection to DB Success");
        } catch (
                SQLException e) {
            throw new SQLException("Failed to create connection to database.", e);
        }

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MySQLCon con = new MySQLCon();
    }

}