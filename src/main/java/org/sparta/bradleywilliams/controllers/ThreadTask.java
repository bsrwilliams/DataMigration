package org.sparta.bradleywilliams.controllers;

import org.sparta.bradleywilliams.models.Employee;

import java.util.ArrayList;
import java.util.List;

public class ThreadTask implements Runnable {
    private List<Employee> employees;

    public ThreadTask(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public void run() {
        EmployeeManager.insertBatchWithThreads((ArrayList<Employee>) employees);
    }
}
