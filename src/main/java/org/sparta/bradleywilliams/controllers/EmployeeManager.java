package org.sparta.bradleywilliams.controllers;

import org.sparta.bradleywilliams.models.CSVReader;
import org.sparta.bradleywilliams.models.Employee;
import org.sparta.bradleywilliams.models.EmployeeDAO;
import org.sparta.bradleywilliams.views.Printer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmployeeManager {
    private static List<Employee> employees;

    public void getEmployees(String url) {
        CSVReader reader = new CSVReader();
        Map<Integer, Employee> employeeMap = reader.getCSValues(url);
        employees = new ArrayList<>(employeeMap.values());
    }

    public static void insertBatch(ArrayList<Employee> employees) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.clearData();
        employeeDAO.addBatch(employees);
    }

    public void InsertUsingThreads() {
        ThreadTasks[] threads = new ThreadTasks[5];
        int tasksPerThread = (int) Math.ceil(employees.size() / (double) threads.length);

        long start = System.nanoTime();

        for (int i = 0; i < threads.length; i++) {
            int lowerBound = tasksPerThread * i;
            int upperBound = tasksPerThread * (i+1);

            if (lowerBound > employees.size()) {
                break;
            }

            if (upperBound >= employees.size()) {
                upperBound = employees.size()-1;
            }
            threads[i] = new ThreadTasks(new ArrayList<>(employees.subList(lowerBound, upperBound)));
            threads[i].run();
        }

        long end = System.nanoTime();
        Printer.printMessage("Time = " + (end-start)/1000000);
    }
}
