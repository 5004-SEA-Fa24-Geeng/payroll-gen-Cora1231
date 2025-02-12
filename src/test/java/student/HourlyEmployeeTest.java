package student;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the HourlyEmployee class.
 */
public class HourlyEmployeeTest {

    /**
     * Tests that the basic getters return the expected values.
     */
    @Test
    public void testBasicGetters() {
        HourlyEmployee emp = new HourlyEmployee("Test Hourly", "H001", 20.0, 1000.0, 200.0, 5.0);
        assertEquals("Test Hourly", emp.getName());
        assertEquals("H001", emp.getID());
        assertEquals(20.0, emp.getPayRate(), 0.001);
        assertEquals("Hourly", emp.getEmployeeType());
        assertEquals(1000.0, emp.getYTDEarnings(), 0.001);
        assertEquals(200.0, emp.getYTDTaxesPaid(), 0.001);
        assertEquals(5.0, emp.getPretaxDeductions(), 0.001);
    }

    /**
     * Tests the gross pay calculation for regular hours (≤ 40).
     */
    @Test
    public void testCalculateGrossPay_RegularHours() {
        HourlyEmployee emp = new HourlyEmployee("Test Hourly", "H001", 20.0, 0, 0, 5.0);
        // For 40 hours, gross pay = 20.0 * 40 = 800.0.
        double grossPay = emp.calculateGrossPay(40);
        assertEquals(800.0, grossPay, 0.001, "Gross pay for 40 hours should be 800.0");
    }

    /**
     * Tests the gross pay calculation for overtime hours (> 40).
     */
    @Test
    public void testCalculateGrossPay_Overtime() {
        HourlyEmployee emp = new HourlyEmployee("Test Hourly", "H001", 20.0, 0, 0, 5.0);
        // For 45 hours:
        // Regular: 40 * 20 = 800.0
        // Overtime: 5 * 20 * 1.5 = 150.0
        // Total gross pay = 800.0 + 150.0 = 950.0
        double grossPay = emp.calculateGrossPay(45);
        assertEquals(950.0, grossPay, 0.001, "Gross pay for 45 hours should be 950.0");
    }

    /**
     * Tests the tax and net pay calculations for a 40-hour work week.
     * Calculation:
     * - Gross pay = 20 * 40 = 800.0
     * - Taxable amount = gross pay - pretax deductions = 800.0 - 5.0 = 795.0
     * - Taxes = 795.0 * 0.2265 ≈ 180.2475, rounded to 180.25
     * - Net pay = gross pay - taxes - pretax deductions = 800.0 - 180.25 - 5.0 = 614.75
     */
    @Test
    public void testGetTaxesAndNetPay_RegularHours() {
        HourlyEmployee emp = new HourlyEmployee("Test Hourly", "H001", 20.0, 0, 0, 5.0);
        double expectedTaxes = 180.25;
        double expectedNetPay = 614.75;
        double actualTaxes = emp.getTaxesPaid(40);
        double actualNetPay = emp.getNetPay(40);
        assertEquals(expectedTaxes, actualTaxes, 0.01, "Taxes for 40 hours");
        assertEquals(expectedNetPay, actualNetPay, 0.01, "Net pay for 40 hours");
    }

    /**
     * Tests the tax and net pay calculations for an overtime scenario (45 hours).
     * Calculation:
     * - Gross pay = 950.0 (as calculated above)
     * - Taxable amount = 950.0 - 5.0 = 945.0
     * - Taxes = 945.0 * 0.2265 ≈ 214.0425, rounded to 214.04
     * - Net pay = 950.0 - 214.04 - 5.0 = 730.96
     */
    @Test
    public void testGetTaxesAndNetPay_Overtime() {
        HourlyEmployee emp = new HourlyEmployee("Test Hourly", "H001", 20.0, 0, 0, 5.0);
        double expectedTaxes = 214.04;
        double expectedNetPay = 730.96;
        double actualTaxes = emp.getTaxesPaid(45);
        double actualNetPay = emp.getNetPay(45);
        assertEquals(expectedTaxes, actualTaxes, 0.01, "Taxes for 45 hours");
        assertEquals(expectedNetPay, actualNetPay, 0.01, "Net pay for 45 hours");
    }

    /**
     * Tests that runPayroll returns a PayStub with the updated year-to-date values.
     * For a 40-hour period, with initial YTD earnings of 1000 and YTD taxes of 200:
     * - New net pay = as computed by getNetPay(40) (614.75)
     * - New taxes = as computed by getTaxesPaid(40) (180.25)
     * - Updated YTD earnings = initial + net pay = 1000 + 614.75 = 1614.75
     * - Updated YTD taxes = initial + taxes = 200 + 180.25 = 380.25
     * Assumes that the PayStub's toCSV() method returns a CSV string in the format:
     * "employeeName,netPay,taxes,ytdEarnings,ytdTaxesPaid"
     */
    @Test
    public void testRunPayroll() {
        double initialYTDEarnings = 1000.0;
        double initialYTDTaxes = 200.0;
        HourlyEmployee emp = new HourlyEmployee("Test Hourly", "H001", 20.0,
                initialYTDEarnings, initialYTDTaxes, 5.0);
        IPayStub stub = emp.runPayroll(40);
        assertNotNull(stub, "runPayroll should return a non-null PayStub for positive hours worked");

        double netPay = emp.getNetPay(40);      // Expected 614.75
        double taxes = emp.getTaxesPaid(40);      // Expected 180.25
        double expectedYTDEarnings = initialYTDEarnings + netPay;
        double expectedYTDTaxes = initialYTDTaxes + taxes;

        // Build the expected CSV string.
        String expectedCSV = String.format("%s,%.2f,%.2f,%.2f,%.2f",
                emp.getName(), netPay, taxes, expectedYTDEarnings, expectedYTDTaxes);

        assertEquals(expectedCSV, stub.toCSV(), "PayStub CSV output does not match the expected values");
    }
}
