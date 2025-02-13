# Payroll Generator Design Document


This document is meant to provide a tool for you to demonstrate the design process. You need to work on this before you code, and after have a finished product. That way you can compare the changes, and changes in design are normal as you work through a project. It is contrary to popular belief, but we are not perfect our first attempt. We need to iterate on our designs to make them better. This document is a tool to help you do that.


## (INITIAL DESIGN): Class Diagram

Place your class diagram below. Make sure you check the fil in the browser on github.com to make sure it is rendering correctly. If it is not, you will need to fix it. As a reminder, here is a link to tools that can help you create a class diagram: [Class Resources: Class Design Tools](https://github.com/CS5004-khoury-lionelle/Resources?tab=readme-ov-file#uml-design-tools)

```mermaid
classDiagram
%% Utility class for file operations
class FileUtil {
+EMPLOYEE_HEADER: String
+PAY_STUB_HEADER: String
+readFileToList(file: String): List~String~
+writeFile(outFile: String, lines: List~String~): void
+writeFile(outFile: String, lines: List~String~, backup: boolean): void
}

    %% IEmployee interface definition using a stereotype
    class IEmployee {
      <<interface>>
      +getName(): String
      +getID(): String
      +getPayRate(): double
      +getEmployeeType(): String
      +getYTDEarnings(): double
      +getYTDTaxesPaid(): double
      +getPretaxDeductions(): double
      +runPayroll(hoursWorked: double): IPayStub
      +toCSV(): String
    }

    %% Concrete Employee classes
    class SalaryEmployee {
        -name: String
        -id: String
        -payRate: double
        -ytdEarnings: double
        -ytdTaxesPaid: double
        -pretaxDeductions: double
        +SalaryEmployee(name: String, id: String, payRate: double, ytdEarnings: double, ytdTaxesPaid: double, pretaxDeductions: double)
        +getName(): String
        +getID(): String
        +getPayRate(): double
        +getEmployeeType(): String
        +getYTDEarnings(): double
        +getYTDTaxesPaid(): double
        +getPretaxDeductions(): double
        +runPayroll(hoursWorked: double): IPayStub
        +toCSV(): String
    }

    class HourlyEmployee {
        -name: String
        -id: String
        -payRate: double
        -ytdEarnings: double
        -ytdTaxesPaid: double
        -pretaxDeductions: double
        +HourlyEmployee(name: String, id: String, payRate: double, ytdEarnings: double, ytdTaxesPaid: double, pretaxDeductions: double)
        +getName(): String
        +getID(): String
        +getPayRate(): double
        +getEmployeeType(): String
        +getYTDEarnings(): double
        +getYTDTaxesPaid(): double
        +getPretaxDeductions(): double
        +runPayroll(hoursWorked: double): IPayStub
        +toCSV(): String
    }

    IEmployee <|.. SalaryEmployee
    IEmployee <|.. HourlyEmployee

    %% IPayStub interface definition using a stereotype
    class IPayStub {
      <<interface>>
      +getPay(): double
      +getTaxesPaid(): double
      +toCSV(): String
    }

    %% Concrete PayStub class for generating payroll details
    class PayStub {
      -employeeName: String
      -netPay: double
      -taxes: double
      -ytdEarnings: double
      -ytdTaxesPaid: double
      +getPay(): double
      +getTaxesPaid(): double
      +toCSV(): String
    }
    IPayStub <|.. PayStub

    %% ITimeCard interface definition using a stereotype
    class ITimeCard {
      <<interface>>
      +getEmployeeID(): String
      +getHoursWorked(): double
    }

    %% Builder class for converting CSV lines into objects
    class Builder {
      +buildEmployeeFromCSV(line: String): IEmployee
      +buildTimeCardFromCSV(line: String): ITimeCard
    }

    %% Main driver class for the program
    class PayrollGenerator {
      +main(args: String[]): void
    }

    %% Inner class for processing command-line arguments (renamed for diagram clarity)
    class PayrollGenerator_Arguments {
      -employeeFile: String
      -payrollFile: String
      -timeCards: String
      +getEmployeeFile(): String
      +getPayrollFile(): String
      +getTimeCards(): String
      +printHelp(): void
      +process(args: String[]): PayrollGenerator_Arguments
    }

    %% Relationships
    PayrollGenerator ..> FileUtil : uses
    PayrollGenerator ..> Builder : uses
    PayrollGenerator ..> PayrollGenerator_Arguments : contains
```




## (INITIAL DESIGN): Tests to Write - Brainstorm

Write a test (in english) that you can picture for the class diagram you have created. This is the brainstorming stage in the TDD process. 

> [!TIP]
> As a reminder, this is the TDD process we are following:
> 1. Figure out a number of tests by brainstorming (this step)
> 2. Write **one** test
> 3. Write **just enough** code to make that test pass
> 4. Refactor/update  as you go along
> 5. Repeat steps 2-4 until you have all the tests passing/fully built program

You should feel free to number your brainstorm. 

1. Test that the `Employee` class properly returns `name` from `getName()`
2. Test that the `Employee` class properly returns `id` from `getId()`
3. Test that the `Employee` class properly returns `payRate` from `getPayRate()`
4. Test that the `Employee` class properly returns the correct `employee type` from `getEmployeeType()`



## (FINAL DESIGN): Class Diagram

Go through your completed code, and update your class diagram to reflect the final design. Make sure you check the file in the browser on github.com to make sure it is rendering correctly. It is normal that the two diagrams don't match! Rarely (though possible) is your initial design perfect. 

> [!WARNING]
> If you resubmit your assignment for manual grading, this is a section that often needs updating. You should double check with every resubmit to make sure it is up to date.





## (FINAL DESIGN): Reflection/Retrospective

```mermaid
classDiagram
%% Utility class for file operations
class FileUtil {
+EMPLOYEE_HEADER: String
+PAY_STUB_HEADER: String
+readFileToList(file: String): List~String~
+writeFile(outFile: String, lines: List~String~): void
+writeFile(outFile: String, lines: List~String~, backup: boolean): void
}

    %% IEmployee interface definition using a stereotype
    class IEmployee {
      <<interface>>
      +getName(): String
      +getID(): String
      +getPayRate(): double
      +getEmployeeType(): String
      +getYTDEarnings(): double
      +getYTDTaxesPaid(): double
      +getPretaxDeductions(): double
      +runPayroll(hoursWorked: double): IPayStub
      +toCSV(): String
    }

    %% Concrete Employee classes
    class SalaryEmployee {
        -name: String
        -id: String
        -payRate: double
        -ytdEarnings: double
        -ytdTaxesPaid: double
        -pretaxDeductions: double
        +SalaryEmployee(name: String, id: String, payRate: double, ytdEarnings: double, ytdTaxesPaid: double, pretaxDeductions: double)
        +getName(): String
        +getID(): String
        +getPayRate(): double
        +getEmployeeType(): String
        +getYTDEarnings(): double
        +getYTDTaxesPaid(): double
        +getPretaxDeductions(): double
        +runPayroll(hoursWorked: double): IPayStub
        +toCSV(): String
    }

    class HourlyEmployee {
        -name: String
        -id: String
        -payRate: double
        -ytdEarnings: double
        -ytdTaxesPaid: double
        -pretaxDeductions: double
        +HourlyEmployee(name: String, id: String, payRate: double, ytdEarnings: double, ytdTaxesPaid: double, pretaxDeductions: double)
        +getName(): String
        +getID(): String
        +getPayRate(): double
        +getEmployeeType(): String
        +getYTDEarnings(): double
        +getYTDTaxesPaid(): double
        +getPretaxDeductions(): double
        +runPayroll(hoursWorked: double): IPayStub
        +getNetPay(hoursWorked: double): double
        +calculateGrossPay(hoursWorked : double): double
        +toCSV(): String
    }

    IEmployee <|.. SalaryEmployee
    IEmployee <|.. HourlyEmployee

    %% IPayStub interface definition using a stereotype
    class IPayStub {
      <<interface>>
      +getPay(): double
      +getTaxesPaid(): double
      +toCSV(): String
    }

    %% Concrete PayStub class for generating payroll details
    class PayStub {
      -employeeName: String
      -netPay: double
      -taxes: double
      -ytdEarnings: double
      -ytdTaxesPaid: double
      +getPay(): double
      +getTaxesPaid(): double
      +toCSV(): String
    }
    IPayStub <|.. PayStub

    %% ITimeCard interface definition using a stereotype
    class ITimeCard {
      <<interface>>
      +getEmployeeID(): String
      +getHoursWorked(): double
    }

    %% Builder class for converting CSV lines into objects
    class Builder {
      +buildEmployeeFromCSV(line: String): IEmployee
      +buildTimeCardFromCSV(line: String): ITimeCard
    }

    %% Main driver class for the program
    class PayrollGenerator {
      +main(args: String[]): void
    }

    %% Inner class for processing command-line arguments (renamed for diagram clarity)
    class PayrollGenerator_Arguments {
      -employeeFile: String
      -payrollFile: String
      -timeCards: String
      +getEmployeeFile(): String
      +getPayrollFile(): String
      +getTimeCards(): String
      +printHelp(): void
      +process(args: String[]): PayrollGenerator_Arguments
    }

    %% Relationships
    PayrollGenerator ..> FileUtil : uses
    PayrollGenerator ..> Builder : uses
    PayrollGenerator ..> PayrollGenerator_Arguments : contains
```
> [!IMPORTANT]
> The value of reflective writing has been highly researched and documented within computer science, from learning new information to showing higher salaries in the workplace. For this next part, we encourage you to take time, and truly focus on your retrospective.

### Take time to reflect on how your design has changed. Write in *prose* (i.e. do not bullet point your answers - it matters in how our brain processes the information). Make sure to include what were some major changes, and why you made them. What did you learn from this process? What would you do differently next time? What was the most challenging part of this process? For most students, it will be a paragraph or two. 
* In my final design, I made several significant changes compared to the initial version. One of the most notable modifications was in the HourlyEmployee class, where I added two new methods—getNetPay and calculateGrossPay—to handle the specific payroll calculations for hourly employees. Initially, both SalaryEmployee and HourlyEmployee shared similar attributes and methods, but as I refined the design, I realized that hourly employees required additional functionality to compute their earnings accurately based on hours worked. This change was driven by the need for a more precise and adaptable system that could accommodate the nuances of different employee types.

* Through this iterative process, I learned the importance of flexibility in design. Early on, my focus was on establishing a broad structure, but as the project evolved, I recognized the necessity of refining details to better reflect real-world requirements. If I were to approach a similar project again, I would invest more time in the initial requirements analysis to pinpoint these differences sooner, potentially reducing the need for later modifications. The most challenging aspect of this process was balancing abstraction and specificity—determining which responsibilities should reside in the interfaces versus the concrete classes, and ensuring that each component remained modular yet fully functional. This experience has underscored the value of iterative design and continuous improvement in software development.