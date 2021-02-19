package org.sparta.bradleywilliams.controllers;

import org.sparta.bradleywilliams.models.CSVReader;
import org.sparta.bradleywilliams.models.Employee;
import org.sparta.bradleywilliams.models.EmployeeDAO;
import org.sparta.bradleywilliams.views.Printer;

import java.util.HashMap;
import java.util.Map;

public class EmployeeManager {
    private static Map<Integer, Employee> employees = new HashMap<>();

    public void generateEmployees(String url) {
        CSVReader reader = new CSVReader();
        employees = reader.getCSValues(url);
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.clearData();
        long start = System.nanoTime();
        employeeDAO.insertValues(employees);
        long end = System.nanoTime();
        Printer.printMessage("Time = " + (end-start)/1000000);
    }

    public int getEmployeeCount() {
        return employees.size();
    }
}
