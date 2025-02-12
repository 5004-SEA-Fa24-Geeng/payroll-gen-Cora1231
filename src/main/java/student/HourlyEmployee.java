package student;

import java.math.BigDecimal;

public class HourlyEmployee implements IEmployee {
    String name;
    String id;
    double payRate;
    double ytdEarnings;
    double ytdTaxesPaid;
    double netPay;
    double pretaxDeductions;

    public HourlyEmployee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions) {
        this.name = name;
        this.id = id;
        this.payRate = payRate;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
        this.pretaxDeductions = pretaxDeductions;
    }

    public HourlyEmployee(IEmployee employee) {
        this.name = employee.getName();
        this.id = employee.getID();
        this.payRate = employee.getPayRate();
        this.ytdEarnings = employee.getYTDEarnings();
        this.ytdTaxesPaid = employee.getYTDTaxesPaid();
        this.pretaxDeductions = employee.getPretaxDeductions();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public double getPayRate() {
        return payRate;
    }

    @Override
    public String getEmployeeType() {
        return "Hourly";
    }

    @Override
    public double getYTDEarnings() {
        return ytdEarnings;
    }

    @Override
    public double getYTDTaxesPaid() {
        return ytdTaxesPaid;
    }

    @Override
    public double getPretaxDeductions() {
        return pretaxDeductions;
    }

    @Override
    public IPayStub runPayroll(double hoursWorked) {
        return new PayStub(getName(),getYTDEarnings()+getNetPay(hoursWorked),getYTDTaxesPaid()+getTaxesPaid(hoursWorked),getNetPay(hoursWorked),getTaxesPaid(hoursWorked));
    }

    public double getTaxesPaid(double hoursWorked) {
        BigDecimal taxes = BigDecimal.valueOf((calculateGrossPay(hoursWorked)-getPretaxDeductions())*.2265);
        return taxes.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public double getNetPay(double hoursWorked){
        BigDecimal netPay= BigDecimal.valueOf(calculateGrossPay(hoursWorked)-getTaxesPaid(hoursWorked)-getPretaxDeductions());
        return netPay.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public double calculateGrossPay(double hoursWorked){
        if (hoursWorked > 40){
            return (hoursWorked - 40) * payRate*1.5+payRate*40;
        }
        return hoursWorked * payRate;
    }

    @Override
    public String toCSV() {
        return "";
    }
}
