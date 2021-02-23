package org.sparta.bradleywilliams;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sparta.bradleywilliams.controllers.EmployeeManager;
import org.sparta.bradleywilliams.models.CSVReader;
import org.sparta.bradleywilliams.models.EmployeeDAO;
import org.sparta.bradleywilliams.views.Printer;

public class PerformanceTests {
    private EmployeeManager employeeManager = new EmployeeManager();

    @Test
    @DisplayName("Testing performance of inserting 10K file without threads")
    public void test10KWithoutThreads() {
        Printer.printMessage("Testing 10K file without threads...");
        employeeManager.getEmployees("resources/employees.csv");
        long start = System.nanoTime();
        employeeManager.insertValues();
        long end = System.nanoTime();
        Printer.printMessage("Time taken = " + (end-start)/1000000 + " nanoseconds");
    }

    @Test
    @DisplayName("Testing performance of inserting data with threads")
    public void testInsert10KWithThreads() {
        Printer.printMessage("Testing 10K file with 5 threads...");
        employeeManager.getEmployees("resources/employees.csv");
        long start = System.nanoTime();
        employeeManager.insertWithThreads(5);
        long end = System.nanoTime();
        Printer.printMessage("Time taken = " + (end-start)/1000000 + " nanoseconds");
    }

    @Test
    @DisplayName("Testing performance of inserting 65K file without threads")
    public void test65KWithoutThreads() {
        Printer.printMessage("Testing 65K file without threads...");
        employeeManager.getEmployees("resources/EmployeeRecordsLarge.csv");
        long start = System.nanoTime();
        employeeManager.insertValues();
        long end = System.nanoTime();
        Printer.printMessage("Time taken = " + (end-start)/1000000 + " nanoseconds");
    }

    @Test
    @DisplayName("Testing performance of inserting 65K file with threads")
    public void testInsert65KWithThreads() {
        Printer.printMessage("Testing 65K file with 5 threads...");
        employeeManager.getEmployees("resources/EmployeeRecordsLarge.csv");
        long start = System.nanoTime();
        employeeManager.insertWithThreads(5);
        long end = System.nanoTime();
        Printer.printMessage("Time taken = " + (end-start)/1000000 + " nanoseconds");
    }
}
