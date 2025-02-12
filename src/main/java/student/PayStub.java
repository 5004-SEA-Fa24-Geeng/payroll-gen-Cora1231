package student;

/**
 * Represents a pay stub for an employee.
 */
public class PayStub implements IPayStub {
    /** The name of the employee. */
    private String employeeName;
    /** The net pay for the employee. */
    private double netPay;
    /** The taxes deducted from the pay. */
    private double taxes;
    /** Year-to-date earnings for the employee. */
    private double ytdEarnings;
    /** Year-to-date taxes paid by the employee. */
    private double ytdTaxesPaid;

    /**
     * Constructs a new PayStub with the specified details.
     *
     * @param employeeName the name of the employee
     * @param ytdEarnings the year-to-date earnings
     * @param ytdTaxesPaid the year-to-date taxes paid
     * @param netPay the net pay amount
     * @param taxes the tax amount deducted
     */
    public PayStub(String employeeName, double ytdEarnings, double ytdTaxesPaid, double netPay, double taxes) {
        this.employeeName = employeeName;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
        this.netPay = netPay;
        this.taxes = taxes;
    }

    /**
     * Returns the net pay for the employee.
     *
     * @return the net pay
     */
    @Override
    public double getPay() {
        return netPay;
    }

    /**
     * Returns the taxes deducted from the pay.
     *
     * @return the taxes paid
     */
    @Override
    public double getTaxesPaid() {
        return taxes;
    }

    /**
     * Returns a CSV representation of the pay stub.
     *
     * @return a CSV string with employeeName, netPay, taxes, ytdEarnings, ytdTaxesPaid
     */
    @Override
    public String toCSV() {
        return employeeName + "," + netPay + "," + taxes + "," + ytdEarnings + "," + ytdTaxesPaid;
    }
}
