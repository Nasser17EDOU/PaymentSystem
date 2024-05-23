/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paymentsystem;

//import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


/**
 *
 * @author Tony NDOSS
 */
public class Payment {
    String paymentDate;
    double paymentNetSalary;
    double paymentGrossSalary;
    double paymentTaxSalary;
    double paymentAmountAddedSalary;
    String paymentAmountAddedSalaryReason;
    double paymentAmountDeductedSalary;
    String paymentAmountDeductedSalaryReason;
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy", Locale.getDefault());
    
    public void setPaymentDate(LocalDate paymentDate){this.paymentDate = paymentDate.format(formatter);}
    public String getPaymentDate(){return paymentDate;}
    
    public void setPaymentNetSalary(double paymentNetSalary){this.paymentNetSalary = paymentNetSalary;}
    public double getPaymentNetSalary(){return paymentNetSalary;}
    
    public void setPaymentGrossSalary(double paymentGrossSalary){this.paymentGrossSalary = paymentGrossSalary;}
    public double getPaymentGrossSalary(){return paymentGrossSalary;}
    
    public void setPaymentTaxSalary(double paymentTaxSalary){this.paymentTaxSalary = paymentTaxSalary;}
    public double getPaymentTaxSalary(){return paymentTaxSalary;}
    
    public void setPaymentAmountAddedSalary(double paymentAmountAddedSalary){this.paymentAmountAddedSalary = paymentAmountAddedSalary;}
    public double getPaymentAmountAddedSalary(){return paymentAmountAddedSalary;}
    
    public void setPaymentAmountAddedSalaryReason(String paymentAmountAddedSalaryReason){this.paymentAmountAddedSalaryReason = paymentAmountAddedSalaryReason;}
    public String getPaymentAmountAddedSalaryReason(){return paymentAmountAddedSalaryReason;}
    
    public void setPaymentAmountDeductedSalary(double paymentAmountDeductedSalary){this.paymentAmountDeductedSalary = paymentAmountDeductedSalary;}
    public double getPaymentAmountDeductedSalary(){return paymentAmountDeductedSalary;}
    
    public void setPaymentAmountDeductedSalaryReason(String paymentAmountDeductedSalaryReason){this.paymentAmountDeductedSalaryReason = paymentAmountDeductedSalaryReason;}
    public String getPaymentAmountDeductedSalaryReason(){return paymentAmountDeductedSalaryReason;}
    
    public Payment(LocalDate paymentDate, double paymentNetSalary, double paymentGrossSalary, double paymentTaxSalary, double paymentAmountAddedSalary, String paymentAmountAddedSalaryReason, double paymentAmountDeductedSalary, String paymentAmountDeductedSalaryReason){
        this.paymentDate = paymentDate.format(formatter);
        this.paymentNetSalary = paymentNetSalary;
        this.paymentGrossSalary = paymentGrossSalary;
        this.paymentTaxSalary = paymentTaxSalary;
        this.paymentAmountAddedSalary = paymentAmountAddedSalary;
        this.paymentAmountAddedSalaryReason = paymentAmountAddedSalaryReason;
        this.paymentAmountDeductedSalary = paymentAmountDeductedSalary;
        this.paymentAmountDeductedSalaryReason = paymentAmountDeductedSalaryReason;
    }
    
    
}
