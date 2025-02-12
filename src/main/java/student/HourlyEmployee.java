package student;

import java.math.BigDecimal;

public class HourlyEmployee implements IEmployee {
    String name;
    String id;
    double payRate;
    double ytdEarnings;
    double ytdTaxesPaid;
    double netPay;
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
        return 200;
    }

    @Override
    public IPayStub runPayroll(double hoursWorked) {
        return new PayStub(getName(),getYTDEarnings()+getNetPay(hoursWorked),getYTDTaxesPaid()+getTaxesPaid(hoursWorked),getNetPay(hoursWorked),getTaxesPaid(hoursWorked));
    }

    public double getTaxesPaid(double hoursWorked) {
        BigDecimal taxes = BigDecimal.valueOf(calculateGrossPay( hoursWorked)*.22);
        return taxes.doubleValue();
    }

    public double getNetPay(double hoursWorked){
        BigDecimal netPay= BigDecimal.valueOf(calculateGrossPay(hoursWorked)*.78-getPretaxDeductions());
        return netPay.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public double calculateGrossPay(double hoursWorked){
        if (hoursWorked > 40){
            return (hoursWorked - 40) * payRate+payRate*40;
        }
        return hoursWorked * payRate;
    }

    @Override
    public String toCSV() {
        return "";
    }
}
