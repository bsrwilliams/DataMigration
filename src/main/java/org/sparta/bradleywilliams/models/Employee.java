package org.sparta.bradleywilliams.models;

import java.sql.Date;
import java.time.LocalDate;

public class Employee {
    private int empID;
    private String prefix;
    private String firstName;
    private char middleInitial;
    private String lastName;
    private char gender;
    private String email;
    private final Date dateOfBirth;
    private final Date joinDate;
    private int salary;

    public Employee(int empID, String prefix, String firstName, char middleInitial, String lastName, char gender, String email, LocalDate dateOfBirth, LocalDate joinDate, int salary) {
        this.empID = empID;
        this.prefix = prefix;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.dateOfBirth = Date.valueOf(dateOfBirth);
        this.joinDate = Date.valueOf(joinDate);
        this.salary = salary;
    }

    public int getEmpID() {
        return empID;
    }

    public String getPrefix() {
        return prefix;
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
        return "EmployeeDTO{" +
                "empID=" + empID +
                ", prefix='" + prefix + '\'' +
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
