/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paymentsystem;

import java.io.InputStream;

/**
 *
 * @author Tony NDOSS
 */
public class Employee {

    String employeeId;
    String employeeFullName;
    String employeeCategory;
    double employeePayRatePerHour;
    double employeeExtraTimePayRate;
    int employeeExpectedTimeToWork;
    double employeeTaxRate;
    InputStream employeePicture;

    public Employee(String employeeId, String employeeFullName, String employeeCategory, double employeePayRatePerHour, double employeeExtraTimePayRate, int employeeExpectedTimeToWork, double employeeTaxRate) {

        this.employeeId = employeeId;
        this.employeeFullName = employeeFullName;
        this.employeeCategory = employeeCategory;
        this.employeePayRatePerHour = employeePayRatePerHour;
        this.employeeExtraTimePayRate = employeeExtraTimePayRate;
        this.employeeExpectedTimeToWork = employeeExpectedTimeToWork;
        this.employeeTaxRate = employeeTaxRate;
    }

    public Employee() {
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeFullName(String employeeFullName) {
        this.employeeFullName = employeeFullName;
    }

    public String getEmployeeFullName() {
        return employeeFullName;
    }

    public void setEmployeeCategory(String employeeCategory) {
        this.employeeCategory = employeeCategory;
    }

    public String getEmployeeCategory() {
        return employeeCategory;
    }

    public void setEmployeePayRatePerHour(double employeePayRatePerHour) {
        this.employeePayRatePerHour = employeePayRatePerHour;
    }

    public double getEmployeePayRatePerHour() {
        return employeePayRatePerHour;
    }

    public void setEmployeeExtraTimePayRate(double employeeExtraTimePayRate) {
        this.employeeExtraTimePayRate = employeeExtraTimePayRate;
    }

    public double getEmployeeExtraTimePayRate() {
        return employeeExtraTimePayRate;
    }

    public void setEmployeeExpectedTimeToWork(int employeeExpectedTimeToWork) {
        this.employeeExpectedTimeToWork = employeeExpectedTimeToWork;
    }

    public int getEmployeeExpectedTimeToWork() {
        return employeeExpectedTimeToWork;
    }

    public void setEmployeeTaxRate(double employeeTaxRate) {
        this.employeeTaxRate = employeeTaxRate;
    }

    public double getEmployeeTaxRate() {
        return employeeTaxRate;
    }

    public void setEmployeePicture(InputStream employeePicture) {
        this.employeePicture = employeePicture;
    }

    public InputStream getEmployeePicture() {
        return employeePicture;
    }

}
