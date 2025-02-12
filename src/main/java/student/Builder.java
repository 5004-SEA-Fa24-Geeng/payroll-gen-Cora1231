package student;

/**
 * This is a static class (essentially functions) that will help you build objects from CSV strings.
 * These objects are then used in the rest of the program. Often these builders are associated
 * with the objects themselves and the concept of a factory, but we placed
 * them here to keep the code clean (and to help guide you).
 */
public final class Builder {

    private Builder() {
    }

    /**
     * Builds an employee object from a CSV string.
     *
     * You may end up checking the type of employee (hourly or salary) by looking at the first
     * element of the CSV string. Then building an object specific to that type.
     *
     * @param csv the CSV string
     * @return the employee object
     */
    public static IEmployee buildEmployeeFromCSV(String csv) {
        String[] row = csv.split(",");

        if (row[0].equals("SALARY")) {
            return new SalaryEmployee(
                    row[1],
                    row[2],
                    Double.parseDouble(row[3]),
                    Double.parseDouble(row[5]),
                    Double.parseDouble(row[6]),
                    Double.parseDouble(row[4])
            );
        }
        return new HourlyEmployee(
                row[1],
                row[2],
                Double.parseDouble(row[3]),
                Double.parseDouble(row[5]),
                Double.parseDouble(row[6]),
                Double.parseDouble(row[4])
        );
    }

    /**
     * Converts a TimeCard from a CSV String.
     *
     * @param csv CSV string
     * @return a TimeCard object
     */
    public static ITimeCard buildTimeCardFromCSV(String csv) {
        String[] row = csv.split(",");
        return new TimeCard(row[0], Double.parseDouble(row[1]));
    }
}
