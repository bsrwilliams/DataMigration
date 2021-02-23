package org.sparta.bradleywilliams.controllers;

public class Starter {
    public static void start() {
        EmployeeManager employeeManager = new EmployeeManager();

        // 10K file
        employeeManager.getEmployees("resources/employees.csv");

        // 65K file
        //employeeManager.getEmployees("resources/EmployeeRecordsLarge.csv");

        // Without Threads
        //employeeManager.insertValues();

        // With Threads
        //employeeManager.insertWithThreads(10);

    }
}
