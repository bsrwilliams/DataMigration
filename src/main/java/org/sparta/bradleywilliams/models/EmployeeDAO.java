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
import java.util.Map;
import java.util.Properties;

public class EmployeeDAO {
    private final String URL = "jdbc:mysql://localhost:3306/mylocal?rewriteBatchedStatements=true";
    private Connection connection = null; // instance of a connection
    private final Properties properties = new Properties(); // instance of properties
    private static final Logger logger = LogManager.getLogger(EmployeeDAO.class);

    private Connection connectToDatabase() {
        try {
            properties.load(new FileReader("resources/login.properties"));
            connection = DriverManager.getConnection(URL, properties.getProperty("username"), properties.getProperty("password"));
        } catch (IOException e) {
            logger.error("Error connecting to database!");
            e.printStackTrace();
        } catch (SQLException e) {
            logger.error("Error in SQL syntax!");
            e.printStackTrace();
        }
        return connection;
    }

    public void addBatch(ArrayList<Employee> employees) {
        String query = "INSERT INTO Employees(EmpID, name_prefix, first_name, middle_initial, last_name, gender, email, date_of_birth, join_date, salary) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connectToDatabase().prepareStatement(query);
            for (Employee employee : employees) {
                preparedStatement.setInt(1, employee.getEmployeeID());
                preparedStatement.setString(2, employee.getTitle());
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
            logger.error("Error in SQL syntax!");
            e.printStackTrace();
        }
    }

    public void clearData() {
        String query = "TRUNCATE Employees";
        try {
            PreparedStatement preparedStatement = connectToDatabase().prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error in SQL syntax!");
            e.printStackTrace();
        }
    }

}
