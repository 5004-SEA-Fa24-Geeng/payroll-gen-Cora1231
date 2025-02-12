package student;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalaryEmployeeTest {

    @Test
    void runPayroll() {
        SalaryEmployee emp = new SalaryEmployee("Test Employee", "001", 2400, 0, 0, 0);
        double expectedTaxes = 2400/24 * 0.22;

        // Act: get the calculated taxes.
        double actualTaxes = emp.getTaxesPaid();

        // Assert: verify the calculated taxes match expected value.
        assertEquals(expectedTaxes, actualTaxes, 0.001, "Taxes paid should equal payRate * 0.22");
    }
}