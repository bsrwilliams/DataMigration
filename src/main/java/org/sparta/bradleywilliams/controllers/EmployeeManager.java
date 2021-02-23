package org.sparta.bradleywilliams.controllers;

import org.sparta.bradleywilliams.models.CSVReader;
import org.sparta.bradleywilliams.models.EmployeeDAO;
import org.sparta.bradleywilliams.models.Employee;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class EmployeeManager {
    private static EmployeeDAO employeeDAO = new EmployeeDAO();
    private ExecutorService threads;
    private ArrayList<Employee> employees;

    public void getEmployees(String url) {
        employeeDAO.clearData();
        CSVReader reader = new CSVReader();
        employees = new ArrayList<>(reader.readValues(url).values());
    }

    public void insertValues() {
        employeeDAO.insertData(employees);
    }

    public void insertWithThreads(int numberOfThreads) {
        if (numberOfThreads <= 0) {
            insertValues();
        }

        ThreadTask[] threads = new ThreadTask[numberOfThreads];
        int tasksPerThread = (int) Math.ceil(employees.size() / (double) numberOfThreads);

        // Assigning tasks to threads simultaneously and starting them
        for (int i = 0; i < numberOfThreads; i++) {
            int fromIndex = tasksPerThread * i;
            int toIndex = tasksPerThread * (i + 1);

            if (fromIndex > employees.size()) {
                break;
            }

            if (toIndex >= employees.size()) {
                toIndex = employees.size();
            }

            threads[i] = new ThreadTask(new ArrayList<>(employees.subList(fromIndex, toIndex)));
            threads[i].run();
        }
    }

    public static void insertBatchWithThreads(ArrayList<Employee> employees) {
        employeeDAO.insertData(employees);
    }


}
