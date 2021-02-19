package org.sparta.bradleywilliams.controllers;

public class Starter {
    public static void start() {
        EmployeeManager employeeManager = new EmployeeManager();
        employeeManager.generateEmployees("resources/employees.csv");
    }
}
