package student;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the SalaryEmployee class.
 */
public class SalaryEmployeeTest {

    /**
     * Test basic getters: name, id, pay rate, and employee type.
     */
    @Test
    public void testBasicGetters() {
        SalaryEmployee emp = new SalaryEmployee("Test Salary", "S001", 2400, 0, 0, 10);
        assertEquals("Test Salary", emp.getName());
        assertEquals("S001", emp.getID());
        assertEquals(2400, emp.getPayRate(), 0.001);
        assertEquals("SALARY", emp.getEmployeeType());
    }

    /**
     * Test that getYTDEarnings() returns the expected value.
     * Note: In this implementation, getYTDEarnings() is defined as payRate * 24.
     */
    @Test
    public void testGetYTDEarnings() {
        SalaryEmployee emp = new SalaryEmployee("Test Salary", "S001", 2400, 0, 0, 10);
        double expectedYtdEarnings = 2400 * 24; // 57600
        assertEquals(expectedYtdEarnings, emp.getYTDEarnings(), 0.001);
    }

    /**
     * Test getTaxesPaid() calculation.
     * Calculation:
     *   gross pay per period = payRate / 24 = 2400 / 24 = 100.0
     *   taxable portion = gross pay - pretaxDeductions = 100.0 - 10 = 90.0
     *   taxes = 90.0 * 0.2265 = 20.385, rounded to 20.39 (using ROUND_HALF_UP)
     */
    @Test
    public void testGetTaxesPaid() {
        SalaryEmployee emp = new SalaryEmployee("Test Salary", "S001", 2400, 0, 0, 10);
        double expectedTaxes = 20.39;
        assertEquals(expectedTaxes, emp.getTaxesPaid(), 0.01);
    }

    /**
     * Test getNetPay() calculation.
     * Calculation:
     *   gross pay per period = 2400 / 24 = 100.0
     *   taxes = 20.39 (as computed above)
     *   net pay = 100.0 - 20.39 - 10 = 69.61
     */
    @Test
    public void testGetNetPay() {
        SalaryEmployee emp = new SalaryEmployee("Test Salary", "S001", 2400, 0, 0, 10);
        double expectedNetPay = 69.61;
        assertEquals(expectedNetPay, emp.getNetPay(), 0.01);
    }

    /**
     * Test runPayroll() for a positive hoursWorked value.
     * The runPayroll method should return a PayStub with the following fields:
     * - Employee name is the same as the SalaryEmployee's name.
     * - YTD earnings updated to (original ytdEarnings + getNetPay()). In this test, ytdEarnings is 0.
     * - YTD taxes updated to (original ytdTaxesPaid + getTaxesPaid()). In this test, ytdTaxesPaid is 0.
     * - Net pay and taxes are as computed by getNetPay() and getTaxesPaid().
     *
     * This test assumes that the PayStub's toCSV() method returns a CSV string in the format:
     * "employeeName,netPay,taxes,ytdEarnings,ytdTaxesPaid"
     */
    @Test
    public void testRunPayrollPositive() {
        SalaryEmployee emp = new SalaryEmployee("Test Salary", "S001", 2400, 0, 0, 10);
        IPayStub stub = emp.runPayroll(40); // hoursWorked > 0; actual value is irrelevant for salaried employees

        assertNotNull(stub, "runPayroll should return a non-null PayStub for positive hours worked");

        double netPay = emp.getNetPay();      // expected 69.61
        double taxesPaid = emp.getTaxesPaid();  // expected 20.39
        // Since initial ytdEarnings and ytdTaxesPaid are 0, they should equal getNetPay() and getTaxesPaid(), respectively.
        double expectedYtdEarnings = netPay;
        double expectedYtdTaxesPaid = taxesPaid;

        // Build the expected CSV string.
        String expectedCSV = String.format("%s,%.2f,%.2f,%.2f,%.2f",
                emp.getName(), netPay, taxesPaid, expectedYtdEarnings, expectedYtdTaxesPaid);

        assertEquals(expectedCSV, stub.toCSV(), "The PayStub CSV output does not match the expected values");
    }

    /**
     * Test runPayroll() for a non-positive hoursWorked value.
     * When hoursWorked is 0 or negative, runPayroll should return null.
     */
    @Test
    public void testRunPayrollNegativeOrZero() {
        SalaryEmployee emp = new SalaryEmployee("Test Salary", "S001", 2400, 0, 0, 10);
        assertNull(emp.runPayroll(0), "runPayroll should return null when hoursWorked is 0");
        assertNull(emp.runPayroll(-5), "runPayroll should return null when hoursWorked is negative");
    }
}