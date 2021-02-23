package org.sparta.bradleywilliams.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class EmployeeDAO {
    private final String URL = "jdbc:mysql://localhost:3306/mylocal?rewriteBatchedStatements=true";
    private Connection connection = null;
    private final Properties properties = new Properties();
    private static final Logger logger = LogManager.getLogger(EmployeeDAO.class);


    public Connection connectToDatabase() {
        try {
            properties.load(new FileReader("resources/login.properties"));
            connection = DriverManager.getConnection(URL, properties.getProperty("username"), properties.getProperty("password"));
        } catch (SQLException e) {
            logger.error("Error with connecting to the database");
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("Error reading file. Please make sure the file exists.");
            e.printStackTrace();
        }
        return connection;
    }

    public void insertData(ArrayList<Employee> employees) {
        String query = "INSERT INTO Employees VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connectToDatabase().prepareStatement(query);
            for (Employee employee : employees) {
                preparedStatement.setInt(1, employee.getEmpID());
                preparedStatement.setString(2, employee.getPrefix());
                preparedStatement.setString(3, employee.getFirstName());
                preparedStatement.setString(4, Character.toString(employee.getMiddleInitial()));
                preparedStatement.setString(5, employee.getLastName());
                preparedStatement.setString(6, Character.toString(employee.getGender()));
                preparedStatement.setString(7, employee.getEmail());
                preparedStatement.setDate(8, employee.getDateOfBirth());
                preparedStatement.setDate(9, employee.getJoinDate());
                preparedStatement.setInt(10, employee.getSalary());

                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            logger.error("Error executing SQL statement");
            e.printStackTrace();
        }
    }

    public void clearData() {
        String query = "TRUNCATE Employees";
        try {
            PreparedStatement preparedStatement = connectToDatabase().prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            logger.error("Error executing SQL query: " + query);
            e.printStackTrace();
        }
    }


}
