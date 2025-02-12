package student;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalaryEmployee implements IEmployee {
    // The employee's name.
    private final String name;
    // The employee's unique identifier.
    private final String id;
    // The employee's pay rate (annual or monthly, as defined by your CSV data).
    private final double payRate;
    // The pretax deductions for the employee.
    private final double pretaxDeductions;
    // Year-to-date earnings.
    private double ytdEarnings;
    // Year-to-date taxes paid.
    private double ytdTaxesPaid;

    /**
     * Constructs a SalaryEmployee with the specified parameters.
     *
     * @param name             the employee's name
     * @param id               the employee's ID
     * @param payRate          the pay rate for the employee
     * @param ytdEarnings      the year-to-date earnings
     * @param ytdTaxesPaid     the year-to-date taxes paid
     * @param pretaxDeductions the pretax deductions for the employee
     */
    public SalaryEmployee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions) {
        this.name = name;
        this.id = id;
        this.payRate = payRate;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
        this.pretaxDeductions = pretaxDeductions;
    }

    /**
     * Gets the employee's name.
     *
     * @return the employee's name.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the employee's ID.
     *
     * @return the employee's ID.
     */
    @Override
    public String getID() {
        return id;
    }

    /**
     * Gets the employee's pay rate.
     *
     * @return the pay rate.
     */
    @Override
    public double getPayRate() {
        return payRate;
    }

    /**
     * Gets the employee type.
     * For salary employees, this returns "SALARY".
     *
     * @return "SALARY"
     */
    @Override
    public String getEmployeeType() {
        return "SALARY";
    }

    /**
     * Gets the year-to-date earnings.
     *
     * @return the year-to-date earnings.
     */
    @Override
    public double getYTDEarnings() {
        return ytdEarnings;
    }

    /**
     * Gets the year-to-date taxes paid.
     *
     * @return the year-to-date taxes paid.
     */
    @Override
    public double getYTDTaxesPaid() {
        return ytdTaxesPaid;
    }

    /**
     * Gets the pretax deductions for the employee.
     *
     * @return the pretax deductions.
     */
    @Override
    public double getPretaxDeductions() {
        return pretaxDeductions;
    }


    @Override
    public IPayStub runPayroll(double hoursWorked) {
        if (hoursWorked < 0) {
            return null;
        }
        double currentTaxes = getTaxesPaid();
        double currentNetPay = getNetPay();
        ytdEarnings += currentNetPay;
        ytdTaxesPaid += currentTaxes;
        return new PayStub(name, ytdEarnings, ytdTaxesPaid, getNetPay(), getTaxesPaid());
    }

    public double getTaxesPaid() {
        BigDecimal taxes = BigDecimal.valueOf((payRate / 24 - getPretaxDeductions()) * .2265);
        return taxes.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public double getNetPay() {
        BigDecimal netPay = BigDecimal.valueOf(payRate / 24 - getTaxesPaid() - pretaxDeductions);
        return netPay.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * Rounds a given value to two decimal places.
     *
     * @param value the value to round.
     * @return the value rounded to two decimal places.
     */
    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    /**
     * Converts the SalaryEmployee data into a CSV formatted string.
     * <p>
     * The CSV format is:
     * employee_type,name,ID,payRate,pretaxDeductions,YTDEarnings,YTDTaxesPaid
     * </p>
     *
     * @return a CSV string representation of the SalaryEmployee.
     */
    @Override
    public String toCSV() {
        return String.format("%s,%s,%s,%.2f,%.2f,%.2f,%.2f",
                getEmployeeType(), name, id, payRate, pretaxDeductions, ytdEarnings, ytdTaxesPaid);
    }


}
