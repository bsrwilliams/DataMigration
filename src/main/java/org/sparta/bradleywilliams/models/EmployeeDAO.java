package org.sparta.bradleywilliams.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public void insertValues(Map<Integer, Employee> employees) {
        String query = "INSERT INTO Employees(EmpID, name_prefix, first_name, middle_initial, last_name, gender, email, date_of_birth, join_date, salary) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connectToDatabase().prepareStatement(query);
            for (Map.Entry<Integer, Employee> employee : employees.entrySet()) {
                preparedStatement.setInt(1, employee.getValue().getEmployeeID());
                preparedStatement.setString(2, employee.getValue().getTitle());
                preparedStatement.setString(3, employee.getValue().getFirstName());
                preparedStatement.setString(4, Character.toString(employee.getValue().getMiddleInitial()));
                preparedStatement.setString(5, employee.getValue().getLastName());
                preparedStatement.setString(6, Character.toString(employee.getValue().getGender()));
                preparedStatement.setString(7, employee.getValue().getEmail());
                preparedStatement.setDate(8, employee.getValue().getDateOfBirth());
                preparedStatement.setDate(9, employee.getValue().getJoinDate());
                preparedStatement.setInt(10, employee.getValue().getSalary());

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
