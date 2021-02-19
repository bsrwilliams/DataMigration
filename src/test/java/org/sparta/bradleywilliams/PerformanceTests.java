package org.sparta.bradleywilliams;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sparta.bradleywilliams.controllers.EmployeeManager;
import org.sparta.bradleywilliams.views.Printer;

public class PerformanceTests {
    @Test
    @DisplayName("Performance test for 10k file")
    public void test10kFile() {
        Printer.printMessage("Starting test with 10k file...");
        EmployeeManager employeeManager = new EmployeeManager();
        long start = System.nanoTime();
        employeeManager.generateEmployees("resources/employees.csv");
        long end = System.nanoTime();
        Printer.printMessage("Time to complete = " + (end-start)/1000000);
    }

    @Test
    @DisplayName("Performance test for 65k file")
    public void test65kFile() {
        Printer.printMessage("Starting test 65k file...");
        EmployeeManager employeeManager = new EmployeeManager();
        long start = System.nanoTime();
        employeeManager.generateEmployees("resources/EmployeeRecordsLarge.csv");
        long end = System.nanoTime();
        Printer.printMessage("Time to complete = " + (end-start)/1000000);
    }
}
