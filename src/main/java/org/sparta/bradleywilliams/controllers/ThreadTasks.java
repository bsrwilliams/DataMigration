package org.sparta.bradleywilliams.controllers;

import org.sparta.bradleywilliams.models.Employee;

import java.util.ArrayList;

public class ThreadTasks implements Runnable {
    private ArrayList<Employee> employees;

    public ThreadTasks(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public void run() {
        EmployeeManager.insertBatch(employees);
    }
}
