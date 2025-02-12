package student;

/**
 * Represents a time card for an employee.
 */
public class TimeCard implements ITimeCard {

    /**
     * The unique identifier of the employee.
     */
    private String employeeID;

    /**
     * The number of hours worked by the employee.
     */
    private double hoursWorked;

    /**
     * Constructs a TimeCard with the specified employee ID and hours worked.
     *
     * @param employeeID  the employee's unique identifier
     * @param hoursWorked the number of hours worked
     */
    public TimeCard(String employeeID, double hoursWorked) {
        this.employeeID = employeeID;
        this.hoursWorked = hoursWorked;
    }

    /**
     * Returns the employee's unique identifier.
     *
     * @return the employeeID
     */
    @Override
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * Returns the number of hours worked.
     *
     * @return the hoursWorked
     */
    @Override
    public double getHoursWorked() {
        return hoursWorked;
    }
}
