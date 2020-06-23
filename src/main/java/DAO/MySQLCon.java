package DAO;

import java.sql.*;
import java.util.Properties;

/** @author Chistensen, Jacob Kjærby (s174130@student.dtu.dk)*/

public class MySQLCon {
    // Initialize connection variables.
    private String host = "universitydtudb.mysql.database.azure.com";
    private String database = "cdiofinaldb";
    private String user = "myadmin@universitydtudb";
    private String password = "2GpZ#P/h{&";
    private Connection connection = null;
    private static MySQLCon instance;

    private MySQLCon() throws SQLException, ClassNotFoundException {
        setupCon();
    }

    public Statement createStatement() throws DALException {
        try {
            connection.createStatement().executeQuery("SELECT * FROM brugerer");
        } catch (SQLException e) {
            try {
                setupCon();
            } catch (SQLException | ClassNotFoundException ignored) {
                throw new DALException("fejl ved forbindelse til server");
            }
        }
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            throw new DALException("fejl ved forbindelse til server");
        }
    }

    public PreparedStatement createStatement(String statement) throws DALException {
        try {
            connection.prepareStatement("SELECT * FROM brugerer");
        } catch (SQLException e) {
            try {
                setupCon();
            } catch (SQLException | ClassNotFoundException ignored) {
                throw new DALException("fejl ved forbindelse til server");
            }
        }
        try {
            return connection.prepareStatement(statement);
        } catch (SQLException e) {
            throw new DALException("fejl ved forbindelse til server");
        }
    }

    public static MySQLCon getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null)
            instance = new MySQLCon();
        return instance;
    }

    // check that the driver is installed
    private void setupCon() throws SQLException, ClassNotFoundException {
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