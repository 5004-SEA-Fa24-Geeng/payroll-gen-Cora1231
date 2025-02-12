package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents an hourly employee and implements the IEmployee interface.
 * This class calculates payroll based on hours worked, including overtime pay.
 */
public class HourlyEmployee implements IEmployee {

    /** The employee's name. */
    private final String name;

    /** The employee's unique identifier. */
    private final String id;

    /** The employee's pay rate per hour. */
    private final double payRate;

    /** The employee's year-to-date earnings. */
    private  double ytdEarnings;

    /** The employee's year-to-date taxes paid. */
    private  double ytdTaxesPaid;

    /** The net pay for the current period (not used directly in calculations). */
    private double netPay;

    /** The pretax deductions for the employee. */
    private final double pretaxDeductions;

    /**
     * Constructs an HourlyEmployee with the specified parameters.
     *
     * @param name              the employee's name
     * @param id                the employee's ID
     * @param payRate           the pay rate per hour
     * @param ytdEarnings       the year-to-date earnings
     * @param ytdTaxesPaid      the year-to-date taxes paid
     * @param pretaxDeductions  the pretax deductions for the employee
     */
    public HourlyEmployee(String name, String id, double payRate,
                          double ytdEarnings, double ytdTaxesPaid,
                          double pretaxDeductions) {
        this.name = name;
        this.id = id;
        this.payRate = payRate;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
        this.pretaxDeductions = pretaxDeductions;
    }

    /**
     * Returns the employee's name.
     *
     * @return the employee's name.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the employee's ID.
     *
     * @return the employee's ID.
     */
    @Override
    public String getID() {
        return id;
    }

    /**
     * Returns the employee's pay rate per hour.
     *
     * @return the pay rate.
     */
    @Override
    public double getPayRate() {
        return payRate;
    }

    /**
     * Returns the employee type.
     *
     * @return "Hourly" as the employee type.
     */
    @Override
    public String getEmployeeType() {
        return "HOURLY";
    }

    /**
     * Returns the year-to-date earnings.
     *
     * @return the YTD earnings.
     */
    @Override
    public double getYTDEarnings() {
        return ytdEarnings;
    }

    /**
     * Returns the year-to-date taxes paid.
     *
     * @return the YTD taxes paid.
     */
    @Override
    public double getYTDTaxesPaid() {
        return ytdTaxesPaid;
    }

    /**
     * Returns the pretax deductions for the employee.
     *
     * @return the pretax deductions.
     */
    @Override
    public double getPretaxDeductions() {
        return pretaxDeductions;
    }

    /**
     * Processes payroll for the current period and returns a PayStub.
     * The new PayStub includes updated YTD earnings and taxes.
     *
     * @param hoursWorked the hours worked in the current period.
     * @return a PayStub with the current period's payroll details.
     */
    @Override
    public IPayStub runPayroll(double hoursWorked) {
        ytdEarnings += getNetPay(hoursWorked);
        ytdTaxesPaid += getNetPay(hoursWorked);
        return new PayStub(
                getName(),
                getYTDEarnings(),
                getYTDTaxesPaid(),
                getNetPay(hoursWorked),
                getTaxesPaid(hoursWorked)
        );
    }

    /**
     * Calculates the taxes paid for the given hours worked.
     * The taxable amount is calculated as (gross pay - pretax deductions),
     * and taxes are computed at 22.65% of that amount.
     *
     * @param hoursWorked the hours worked.
     * @return the taxes paid, rounded to two decimal places.
     */
    public double getTaxesPaid(double hoursWorked) {
        BigDecimal taxes = BigDecimal.valueOf(
                (calculateGrossPay(hoursWorked) - getPretaxDeductions()) * 0.2265
        );
        return taxes.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * Calculates the net pay for the given hours worked.
     * The net pay is computed as (gross pay - taxes - pretax deductions).
     *
     * @param hoursWorked the hours worked.
     * @return the net pay, rounded to two decimal places.
     */
    public double getNetPay(double hoursWorked) {
        BigDecimal netPay = BigDecimal.valueOf(
                calculateGrossPay(hoursWorked) - getTaxesPaid(hoursWorked) - getPretaxDeductions()
        );
        return netPay.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * Calculates the gross pay based on the hours worked.
     * Overtime (hours above 40) is paid at 1.5 times the regular rate.
     *
     * @param hoursWorked the hours worked.
     * @return the calculated gross pay.
     */
    public double calculateGrossPay(double hoursWorked) {
        if (hoursWorked > 40) {
            return (hoursWorked - 40) * payRate * 1.5 + (40 * payRate);
        }
        return hoursWorked * payRate;
    }

    /**
     * Returns a CSV representation of the HourlyEmployee.
     * The CSV format is:
     * "employee_type,name,ID,payRate,pretaxDeductions,YTDEarnings,YTDTaxesPaid"
     *
     * @return a CSV string representation of the HourlyEmployee.
     */
    @Override
    public String toCSV() {
        return String.format(
                getEmployeeType(), name, id, payRate, pretaxDeductions, ytdEarnings, ytdTaxesPaid);
    }
}
