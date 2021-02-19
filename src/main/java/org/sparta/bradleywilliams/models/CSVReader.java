package org.sparta.bradleywilliams.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CSVReader {
    private static final Logger logger = LogManager.getLogger(CSVReader.class);

    private static ArrayList<Employee> duplicateEmployees = new ArrayList<>();


    public Map<Integer, Employee> getCSValues(String url) {
        Map<Integer, Employee> employees = new HashMap<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(url));
            bufferedReader.readLine();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] records = line.split(",");
                Employee employee = new Employee(Integer.parseInt(records[0]), records[1], records[2], records[3].charAt(0), records[4], records[5].charAt(0), records[6], formatDate(records[7]), formatDate(records[8]), Integer.parseInt(records[9]));
                if (employees.containsKey(employee.getEmployeeID())) {
                    duplicateEmployees.add(employee);
                } else {
                    employees.put(employee.getEmployeeID(), employee);
                }
            }
        } catch (IOException e) {
            logger.error("Error reading file!");
            e.printStackTrace();
        }
        return employees;
    }

    private static LocalDate formatDate(String unformattedDate) {
        return LocalDate.parse(unformattedDate, DateTimeFormatter.ofPattern("M/d/yyyy"));
    }

    private ArrayList<Employee> getDuplicateEmployees() {
        return duplicateEmployees;
    }
}