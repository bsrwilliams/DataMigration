package org.sparta.bradleywilliams.models;

import java.sql.Date;
import java.time.LocalDate;

public class Employee {
    private int employeeID;
    private String title;
    private String firstName;
    private char middleInitial;
    private String lastName;
    private char gender;
    private String email;
    private final Date dateOfBirth;
    private final Date joinDate;
    private int salary;

    public Employee(int employeeID, String title, String firstName, char middleInitial, String lastName, char gender, String email, LocalDate dateOfBirth, LocalDate joinDate, int salary) {
        this.employeeID = employeeID;
        this.title = title;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.dateOfBirth = Date.valueOf(dateOfBirth);
        this.joinDate = Date.valueOf(joinDate);
        this.salary = salary;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstName() {
        return firstName;
    }

    public char getMiddleInitial() {
        return middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public char getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", title='" + title + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleInitial=" + middleInitial +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", joinDate=" + joinDate +
                ", salary=" + salary +
                '}';
    }
}
