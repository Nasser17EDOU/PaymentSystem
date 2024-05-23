/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paymentsystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 *
 * @author Tony NDOSS
 */
public class SelectedEmployee{
    
    static String employeeId;
    static String employeeFullName;
    static String employeeCategory;
    static double employeePayRatePerHour;
    static double employeeExtraTimePayRate;
    static int employeeExpectedTimeToWork;
    static double employeeTaxRate;
    static File employeePicture;

    public SelectedEmployee(String employeeId, String employeeFullName, String employeeCategory, double employeePayRatePerHour, double employeeExtraTimePayRate, int employeeExpectedTimeToWork, double employeeTaxRate) {

        SelectedEmployee.employeeId = employeeId;
        SelectedEmployee.employeeFullName = employeeFullName;
        SelectedEmployee.employeeCategory = employeeCategory;
        SelectedEmployee.employeePayRatePerHour = employeePayRatePerHour;
        SelectedEmployee.employeeExtraTimePayRate = employeeExtraTimePayRate;
        SelectedEmployee.employeeExpectedTimeToWork = employeeExpectedTimeToWork;
        SelectedEmployee.employeeTaxRate = employeeTaxRate;
    }

    public SelectedEmployee() {
    }

    public void setEmployeeId(String employeeId) {
        SelectedEmployee.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeFullName(String employeeFullName) {
        SelectedEmployee.employeeFullName = employeeFullName;
    }

    public String getEmployeeFullName() {
        return employeeFullName;
    }

    public void setEmployeeCategory(String employeeCategory) {
        SelectedEmployee.employeeCategory = employeeCategory;
    }

    public String getEmployeeCategory() {
        return employeeCategory;
    }

    public void setEmployeePayRatePerHour(double employeePayRatePerHour) {
        SelectedEmployee.employeePayRatePerHour = employeePayRatePerHour;
    }

    public double getEmployeePayRatePerHour() {
        return employeePayRatePerHour;
    }

    public void setEmployeeExtraTimePayRate(double employeeExtraTimePayRate) {
        SelectedEmployee.employeeExtraTimePayRate = employeeExtraTimePayRate;
    }

    public double getEmployeeExtraTimePayRate() {
        return employeeExtraTimePayRate;
    }

    public void setEmployeeExpectedTimeToWork(int employeeExpectedTimeToWork) {
        SelectedEmployee.employeeExpectedTimeToWork = employeeExpectedTimeToWork;
    }

    public int getEmployeeExpectedTimeToWork() {
        return employeeExpectedTimeToWork;
    }

    public void setEmployeeTaxRate(double employeeTaxRate) {
        SelectedEmployee.employeeTaxRate = employeeTaxRate;
    }

    public double getEmployeeTaxRate() {
        return employeeTaxRate;
    }

    public void setEmployeePicture(File employeePicture) {
        SelectedEmployee.employeePicture = employeePicture;
    }

    public File getEmployeePicture() {
        return employeePicture;
    }
}
