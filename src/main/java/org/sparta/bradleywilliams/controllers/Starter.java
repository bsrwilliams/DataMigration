package org.sparta.bradleywilliams.controllers;

import org.sparta.bradleywilliams.models.Employee;

import java.util.ArrayList;

public class Starter {
    public static void start() {
        EmployeeManager employeeManager = new EmployeeManager();
        employeeManager.getEmployees("resources/employees.csv");
        employeeManager.InsertUsingThreads();
    }
}
